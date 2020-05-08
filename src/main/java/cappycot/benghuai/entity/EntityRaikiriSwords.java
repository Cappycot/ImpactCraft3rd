package cappycot.benghuai.entity;

import java.util.UUID;

import cappycot.benghuai.util.Alliance;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityRaikiriSwords extends EntityHonkaiWeapon implements IEntityAdditionalSpawnData {

	private Entity attached = null; // Was of type EntityLivingBase.
	private UUID attachedUUID = null; // Entity UUID, not player UUID.
	private float bladeDamage = 0F;
	private int lifeTicks = 120;

	public EntityRaikiriSwords(World world) {
		super(world);
	}

	public EntityRaikiriSwords(World world, EntityPlayer owner, Entity attached, float bladeDamage, int lifeTicks) {
		super(world, (EntityPlayer) (owner == null ? (attached instanceof EntityPlayer ? attached : null) : owner));
		this.attached = attached;
		this.setPositionAndRotation(attached.posX, attached.posY, attached.posZ, attached.rotationYaw, 0);
		this.bladeDamage = bladeDamage;
		this.lifeTicks = lifeTicks;
	}

	@Override
	public void entityInit() {
		this.setEntityInvulnerable(true);
		this.setNoGravity(true);
		this.setSize(6.0F, 1.0F);
		this.isImmuneToFire = true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		EntityPlayer owner = getOwner();
		lifeTicks--;
		if (lifeTicks < 0)
			this.setDead();
		else {
			// Once client side rendering is completely fixed, we can move setPosition to be
			// done server side once every five ticks instead of being done every tick.
			if (attached != null)
				this.setPosition(attached.posX, attached.posY, attached.posZ); // TODO: Move when client sync fixed.
			else if (attachedUUID != null)
				tryAttach();
			if (!this.world.isRemote && owner != null && lifeTicks % 5 == 0) {
				if (attached != null)
					this.setPosition(attached.posX, attached.posY, attached.posZ);
				else if (attachedUUID != null)
					tryAttach();
				for (EntityLivingBase e : this.world.getEntitiesWithinAABB(EntityLivingBase.class,
						this.getEntityBoundingBox())) {
					if (e == owner || e == attached || e.getIsInvulnerable())
						continue;
					else if (Alliance.shouldNotHarmByPlayer(e, owner))
						continue;
					// Calculate distance since this is a circular weapon.
					if (Math.sqrt(Math.pow(this.posX - e.posX, 2) + Math.pow(this.posZ - e.posZ, 2)) > e.width + 3.0F)
						continue;
					// TODO: Custom DamageSource?
					e.hurtResistantTime = 0;
					e.hurtTime = 0;
					e.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, attached), bladeDamage);
				}
			}
		}
	}

	public Entity getAttached() {
		return attached;
	}

	public void setAttached(Entity attached) {
		this.attached = attached;
	}

	private void tryAttach() {
		for (Entity e : this.world.loadedEntityList) {
			if (e.getUniqueID().equals(attachedUUID)) {
				attached = e;
				break;
			}
		}
		// TODO: Check for failed attachment.
		if (attached == null)
			lifeTicks = 0;
		// for (EntityLivingBase e :
		// this.world.getEntitiesWithinAABB(EntityLivingBase.class,
		// this.getEntityBoundingBox().grow(0, 2.5, 0))) {
		// if (e.getUniqueID().equals(attachedUUID)) {
		// attached = e;
		// break;
		// }
		// }
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		if (compound.hasUniqueId("attached"))
			attachedUUID = compound.getUniqueId("attached");
		if (compound.hasKey("lifespan"))
			lifeTicks = compound.getInteger("lifespan");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		if (attached != null)
			compound.setUniqueId("attached", attached.getUniqueID());

		compound.setInteger("lifespan", lifeTicks);
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		super.writeSpawnData(buffer);
		UUID toSend = attached != null ? attached.getUniqueID() : attachedUUID;
		buffer.writeBoolean(toSend != null);
		if (toSend != null) {
			buffer.writeLong(toSend.getMostSignificantBits());
			buffer.writeLong(toSend.getLeastSignificantBits());
		}
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		super.readSpawnData(additionalData);
		boolean notNull = additionalData.readBoolean();
		if (notNull) {
			attachedUUID = new UUID(additionalData.readLong(), additionalData.readLong());
			tryAttach();
		}
	}

	/**
	 * This method is used by the client to forcibly update the position of entities
	 * to match the server. Here we intentionally make this method do nothing to
	 * prevent the blades from twitching on the client's side.
	 * 
	 * This is also a temporary fix until custom packets for entity data are
	 * implemented.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch,
			int posRotationIncrements, boolean teleport) {
		if (attached == null)
			super.setPositionAndRotationDirect(x, y, z, yaw, pitch, posRotationIncrements, teleport);
	}
}
