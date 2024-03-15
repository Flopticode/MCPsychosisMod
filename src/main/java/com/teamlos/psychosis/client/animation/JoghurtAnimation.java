// Save this class in your mod and generate all required imports
package com.teamlos.psychosis.client.animation;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

/**
 * Made with Blockbench 4.9.4
 * Exported for Minecraft version 1.19 or later with Mojang mappings
 * @author Author
 */
public class JoghurtAnimation {
	public static final AnimationDefinition JOGHURT_RUN = AnimationDefinition.Builder.withLength(0.2917F).looping()
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.0833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 20.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.2917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(-4.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.0833F, KeyframeAnimations.posVec(3.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.1667F, KeyframeAnimations.posVec(-6.0F, 2.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.2917F, KeyframeAnimations.posVec(4.0F, 2.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.0833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.2917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 17.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.1667F, KeyframeAnimations.posVec(4.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.2917F, KeyframeAnimations.posVec(-5.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();

		public static final AnimationDefinition IDLE = AnimationDefinition.Builder.withLength(0.0F).looping()
			
			.build();
}