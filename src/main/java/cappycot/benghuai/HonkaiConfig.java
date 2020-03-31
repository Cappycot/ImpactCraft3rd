package cappycot.benghuai;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RangeDouble;
import net.minecraftforge.common.config.Config.RangeInt;

/**
 * All numbers that can be changed in config.
 */
@Config(modid = HonkaiValues.MODID, category = "")
public class HonkaiConfig {

	public static final NuadasGrief NUADAS_GRIEF = new NuadasGrief();
	public static final Raikiri RAIKIRI = new Raikiri();

	public static class NuadasGrief {
		private NuadasGrief() {
		}

		@Comment("Nuada's Grief cooldown in ticks")
		@RangeInt(min = 0)
		public int cooldown = 400;
		@Comment("SP consumed for Nuada's Grief skill")
		@RangeInt(min = 0)
		public int SP = 15;
	}

	public static class Raikiri {
		private Raikiri() {
		}

		@Comment("Raikiri cooldown in ticks")
		@RangeInt(min = 0) // This is non-creative mode cooldown.
		public int cooldown = 500;
		@Comment("SP consumed for Raikiri skill")
		@RangeInt(min = 0)
		public int SP = 18;
		@Comment({ "Entities in the player's party within this range will get blades",
				"Example: If the range is 8, a Wolf owned by the player 8 or less blocks",
				"will gain Raikiri blades." })
		@RangeInt(min = 0)
		public int range = 8;
		@Comment("Damage in half hearts of the Raikiri blades done 4 times/sec")
		@RangeDouble(min = 1.0)
		public double bladeDamage0 = 1.36;
		@RangeDouble(min = 1.0)
		public double bladeDamage1 = 1.90;
		@RangeDouble(min = 1.0)
		public double bladeDamage2 = 2.51;
		@RangeDouble(min = 1.0)
		public double bladeDamage3 = 3.20;
		@Comment("Lifespan of Raikiri blades in ticks")
		@RangeInt(min = 5)
		public int bladeTicks0 = 88;
		@RangeInt(min = 5)
		public int bladeTicks1 = 98;
		@RangeInt(min = 5)
		public int bladeTicks2 = 108;
		@RangeInt(min = 5)
		public int bladeTicks3 = 120;
		@Comment("Additional blade damage per hit in half-hearts")
		@RangeDouble(min = 0)
		public double damage0 = 1D;
		@RangeDouble(min = 0)
		public double damage1 = 2D;
		@RangeDouble(min = 0)
		public double damage2 = 3D;
		@RangeDouble(min = 0)
		public double damage3 = 4D;
	}
}
