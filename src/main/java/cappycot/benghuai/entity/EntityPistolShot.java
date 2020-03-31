package cappycot.benghuai.entity;

import cappycot.benghuai.registry.SoundRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityPistolShot extends EntityArrow {

	public EntityPistolShot(World worldIn) {
		super(worldIn);
	}

	public EntityPistolShot(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityPistolShot(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean canRenderOnFire() {
		return false;
	}

	@Override
	protected ItemStack getArrowStack() {
		return null;
	}

	@Override
	public boolean getIsCritical() {
		return true; // TODO: Debug
	}

	@Override
	public boolean hasNoGravity() {
		return true;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn) {
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (this.ticksExisted > 20)
			this.setDead();
	}

	/**
	 * Called when the arrow hits a block or an entity
	 */
	protected void onHit(RayTraceResult raytraceResultIn) {
		Entity entity = raytraceResultIn.entityHit;

		if (entity != null) {

			DamageSource damagesource;

			if (this.shootingEntity == null) {
				damagesource = DamageSource.causeArrowDamage(this, this);
			} else {
				damagesource = DamageSource.causeArrowDamage(this, this.shootingEntity);
			}

			if (this.isBurning() && !(entity instanceof EntityEnderman)) {
				entity.setFire(5);
			}

			if (entity.attackEntityFrom(damagesource, 4.0F)) {
				if (entity instanceof EntityLivingBase) {
					EntityLivingBase entitylivingbase = (EntityLivingBase) entity;

					if (this.shootingEntity instanceof EntityLivingBase) {
						EnchantmentHelper.applyThornEnchantments(entitylivingbase, this.shootingEntity);
						EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase) this.shootingEntity,
								entitylivingbase);
					}

					this.arrowHit(entitylivingbase);

					if (this.shootingEntity != null && entitylivingbase != this.shootingEntity
							&& entitylivingbase instanceof EntityPlayer
							&& this.shootingEntity instanceof EntityPlayerMP) {
						((EntityPlayerMP) this.shootingEntity).connection
								.sendPacket(new SPacketChangeGameState(6, 0.0F));
					}
				}

				if (!(entity instanceof EntityEnderman)) {
					this.setDead();
				}
			} else
				this.setDead();
		} else // TODO: Glass breaking, etc.
			this.setDead();
	}
}
