package cappycot.benghuai.entity;

import java.util.Iterator;
import java.util.LinkedList;

import cappycot.benghuai.HonkaiValues;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityTimeSlowField extends EntityHonkaiWeapon {

	private int lifeTicks = 200;
	private LinkedList<Entity> slowed = new LinkedList<Entity>();

	public EntityTimeSlowField(World world) {
		super(world);
	}

	public EntityTimeSlowField(World world, EntityPlayer owner) {
		super(world, owner);
	}

	@Override
	protected void entityInit() {
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		EntityPlayer owner = getOwner();
		lifeTicks--;
		if (lifeTicks < 0) {
			for (Entity e : slowed) {
				if (e.getTags().contains(HonkaiValues.TIME_FRACTURE_TAG))
					e.getTags().remove(HonkaiValues.TIME_FRACTURE_TAG);
			}
			this.setDead();
		}
		if (this.isDead)
			return;
		AxisAlignedBB boundingBox = getEntityBoundingBox().grow(16);
		for (Entity e : world.getEntitiesWithinAABBExcludingEntity(this, boundingBox)) {
			if (!(e instanceof EntityPlayer))
				slowed.add(e);
		}
		Iterator<Entity> iter = slowed.iterator();
		while (iter.hasNext()) {
			Entity e = iter.next();
			if (boundingBox.intersects(e.getEntityBoundingBox())) {
				if (!e.getTags().contains(HonkaiValues.TIME_FRACTURE_TAG))
					e.getTags().add(HonkaiValues.TIME_FRACTURE_TAG);
			} else {
				if (e.getTags().contains(HonkaiValues.TIME_FRACTURE_TAG))
					e.getTags().remove(HonkaiValues.TIME_FRACTURE_TAG);
				iter.remove();
			}
		}
	}
}
