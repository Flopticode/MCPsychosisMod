package com.teamlos.psychosis.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

/**
 * Behavior:
 * - Aggressive
 * - Fast running (Speed of a rabbit)
 * - Can be bread with buckets of milk
 */
public class Angel extends WitherBoss
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
		return WitherBoss.createLivingAttributes();
	}
	
	protected PathNavigation createNavigation(Level level)
	{
		return new FlyingPathNavigation(this, level);
	}
	
	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.WITHER_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource p_32750_)
	{
		return SoundEvents.WITHER_HURT;
	}

	protected SoundEvent getDeathSound()
	{
		return SoundEvents.WITHER_DEATH;
	}
	
	protected float getSoundVolume()
	{
		return 1.0F;
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
		super.registerGoals();
	}
	
	public static boolean canSpawn(EntityType<Angel> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos position, RandomSource random)
	{
		return level.getDifficulty() != Difficulty.PEACEFUL && random.nextInt(20) == 0 && checkMobSpawnRules(entityType, level, spawnType, position, random);
	}
	
}
