package com.teamlos.psychosis.entities;

import javax.annotation.Nullable;

import com.teamlos.psychosis.items.ItemRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class Sloth extends Panda
{
	public final AnimationState idleAnimationState = new AnimationState();
	
	public Sloth(EntityType<Sloth> slothType, Level level)
	{
		super(slothType, level);
	}
	public Sloth(Level level, double x, double y, double z)
	{
		this(EntityRegistry.SLOTH.get(), level);
	}
	public Sloth(Level level, BlockPos pos)
	{
		this(level, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
	}
	
	public static AttributeSupplier.Builder createMobAttributes()
	{
		return Panda.createLivingAttributes().add(Attributes.FOLLOW_RANGE, 16.0D).add(Attributes.ATTACK_KNOCKBACK).add(Attributes.MAX_HEALTH, 10);
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
	public Sloth getBreedOffspring(ServerLevel level, AgeableMob otherParent)
	{
		return new Sloth(level, this.blockPosition());
	}
	
	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(2, new SlothBreedGoal(this, 0.2D));
		this.goalSelector.addGoal(3, new SlothAttackGoal(this, (double)0.2F, true));
		this.goalSelector.addGoal(4, new TemptGoal(this, 0.25D, Ingredient.of(ItemRegistry.GIGABYTE_GPU.get()), false));
		
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Sloth.class, true));
		super.registerGoals();
	}
	
	public static boolean canSpawn(EntityType<Sloth> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos position, RandomSource random)
	{
		return Animal.checkAnimalSpawnRules(entityType, level, spawnType, position, random);
	}
	
	static class SlothAttackGoal extends MeleeAttackGoal {
	      private final Sloth sloth;

	      public SlothAttackGoal(Sloth sloth, double p_29270_, boolean p_29271_) {
	         super(sloth, p_29270_, p_29271_);
	         this.sloth = sloth;
	      }

	      public boolean canUse() {
	         return this.sloth.canPerformAction() && super.canUse();
	      }
	   }
	
	static class SlothBreedGoal extends BreedGoal {
	      private final Sloth sloth;
	      private int unhappyCooldown;

	      public SlothBreedGoal(Sloth p_186221_, double p_186222_) {
	         super(p_186221_, p_186222_);
	         this.sloth = p_186221_;
	      }

	      public boolean canUse() {
	         if (super.canUse() && this.sloth.getUnhappyCounter() == 0) {
	            if (!this.canFindBamboo()) {
	               if (this.unhappyCooldown <= this.sloth.tickCount) {
	                  this.sloth.setUnhappyCounter(32);
	                  this.unhappyCooldown = this.sloth.tickCount + 600;
	               }

	               return false;
	            } else {
	               return true;
	            }
	         } else {
	            return false;
	         }
	      }

	      private boolean canFindBamboo() {
	         BlockPos blockpos = this.sloth.blockPosition();
	         BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

	         for(int i = 0; i < 3; ++i) {
	            for(int j = 0; j < 8; ++j) {
	               for(int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
	                  for(int l = k < j && k > -j ? j : 0; l <= j; l = l > 0 ? -l : 1 - l) {
	                     blockpos$mutableblockpos.setWithOffset(blockpos, k, i, l);
	                     if (this.level.getBlockState(blockpos$mutableblockpos).is(Blocks.BAMBOO)) {
	                        return true;
	                     }
	                  }
	               }
	            }
	         }

	         return false;
	      }
	   }
}
