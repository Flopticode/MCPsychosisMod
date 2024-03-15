package com.teamlos.psychosis.entities;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

/**
 * Behavior:
 * - Aggressive
 * - Fast running (Speed of a rabbit)
 * - Can be bread with buckets of milk
 */
public class Joghurt extends Rabbit
{
	public final AnimationState idleAnimationState = new AnimationState();
	
	public Joghurt(EntityType<Joghurt> joghurtType, Level level)
	{
		super(joghurtType, level);
	}
	public Joghurt(Level level, double x, double y, double z)
	{
		this(EntityRegistry.JOGHURT.get(), level);
	}
	public Joghurt(Level level, BlockPos pos)
	{
		this(level, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
	}
	
	public static AttributeSupplier.Builder createMobAttributes()
	{
		return Rabbit.createLivingAttributes().add(Attributes.FOLLOW_RANGE, 16.0D).add(Attributes.ATTACK_KNOCKBACK);
	}
	
	protected PathNavigation createNavigation(Level level)
	{
		return new GroundPathNavigation(this, level);
	}
	
	@Override
	public void tick()
	{
		if(level().isClientSide())
		{
			this.idleAnimationState.animateWhen(!isInWaterOrBubble() && !this.walkAnimation.isMoving(), this.tickCount);
		}
		
		super.tick();
	}
	
	@Nullable
	@Override
	public Joghurt getBreedOffspring(ServerLevel level, AgeableMob otherParent)
	{
		return new Joghurt(level, this.blockPosition());
	}
	
	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 2.2D, false));
		this.goalSelector.addGoal(0, new BreedGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.6D));
		this.goalSelector.addGoal(11, new LookAtPlayerGoal(this, Player.class, 10.0F));
		
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}
	
	public static boolean canSpawn(EntityType<Joghurt> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos position, RandomSource random)
	{
		return Mob.checkMobSpawnRules(entityType, level, spawnType, position, random);
	}
	
	static class JoghurtPanicGoal extends PanicGoal {
	      private final Rabbit rabbit;

	      public JoghurtPanicGoal(Rabbit p_29775_, double p_29776_) {
	         super(p_29775_, p_29776_);
	         this.rabbit = p_29775_;
	      }

	      public void tick() {
	         super.tick();
	         this.rabbit.setSpeedModifier(this.speedModifier);
	      }
	   }
}
