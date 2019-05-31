package com.cappycot.benghuai.entity;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

/**
 * Base class for summoned entities.
 */
public abstract class EntityHonkaiWeapon extends Entity implements IEntityAdditionalSpawnData {

	private EntityPlayer owner = null;
	private UUID ownerUUID = null;
	// private int lifeTicks;

	public EntityHonkaiWeapon(World world) {
		super(world);
	}

	public EntityHonkaiWeapon(World world, EntityPlayer owner) {
		super(world);
		this.owner = owner;
	}

	public EntityPlayer getOwner() {
		return owner;
	}

	@Override
	public void onUpdate() {
		if (owner == null && ownerUUID != null && this.world.playerEntities.size() > 0)
			owner = this.world.getPlayerEntityByUUID(ownerUUID);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		if (compound.hasUniqueId("owner"))
			ownerUUID = compound.getUniqueId("owner");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		if (owner != null)
			compound.setUniqueId("owner", owner.getUniqueID());
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeBoolean(owner != null);
		UUID toSend = owner != null ? owner.getUniqueID() : ownerUUID;
		if (toSend != null) {
			buffer.writeLong(toSend.getMostSignificantBits());
			buffer.writeLong(toSend.getLeastSignificantBits());
		}
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		if (additionalData.readBoolean()) {
			long mSig = additionalData.readLong();
			long lSig = additionalData.readLong();
			owner = this.world.getPlayerEntityByUUID(new UUID(mSig, lSig));
		}
	}
}
