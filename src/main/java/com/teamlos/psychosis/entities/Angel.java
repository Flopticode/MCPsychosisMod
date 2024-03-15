package com.teamlos.psychosis.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

/**
 * Behavior:
 * - Aggressive
 * - Fast running (Speed of a rabbit)
 * - Can be bread with buckets of milk
 */
public class Angel extends Ghast
{
	public final AnimationState idleAnimationState = new AnimationState();
	
	public Angel(EntityType<Angel> joghurtType, Level level)
	{
		super(joghurtType, level);
	}
	public Angel(Level level, double x, double y, double z)
	{
		this(EntityRegistry.ANGEL.get(), level);
	}
	public Angel(Level level, BlockPos pos)
	{
		this(level, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
	}
	
	public static AttributeSupplier.Builder createMobAttributes()
	{
		return Rabbit.createLivingAttributes().add(Attributes.FOLLOW_RANGE, 16.0D).add(Attributes.ATTACK_KNOCKBACK);
	}
	
	protected PathNavigation createNavigation(Level level)
	{
		return new FlyingPathNavigation(this, level);
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
	
	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(11, new LookAtPlayerGoal(this, Player.class, 10.0F));
	    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}
	
	public static boolean canSpawn(EntityType<Angel> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos position, RandomSource random)
	{
		return level.getDifficulty() != Difficulty.PEACEFUL && random.nextInt(20) == 0 && checkMobSpawnRules(entityType, level, spawnType, position, random);
	}
	
}
