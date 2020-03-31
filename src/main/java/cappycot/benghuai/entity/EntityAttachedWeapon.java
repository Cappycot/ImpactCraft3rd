package cappycot.benghuai.entity;

import java.util.UUID;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * A summoned entity that is attached to some living entity.
 */
public abstract class EntityAttachedWeapon extends EntityHonkaiWeapon {

	private EntityLivingBase attached = null;
	private UUID attachedUUID = null;

	public EntityAttachedWeapon(World world) {
		super(world);
	}

	public EntityAttachedWeapon(World world, EntityPlayer owner) {
		super(world, owner);
	}

}
