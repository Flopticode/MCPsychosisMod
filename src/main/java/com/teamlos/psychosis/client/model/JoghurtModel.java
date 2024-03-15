// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
package com.teamlos.psychosis.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamlos.psychosis.client.animation.JoghurtAnimation;
import com.teamlos.psychosis.entities.Joghurt;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class JoghurtModel<T extends Joghurt> extends HierarchicalModel<Joghurt> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "custommodel"), "main");
	private final ModelPart root;

	public JoghurtModel(ModelPart root) {
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone3 = root.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(1, 1).addBox(0.0F, -16.0F, 0.0F, 1.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone2 = root.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(6, 10).addBox(-1.0F, -21.0F, -3.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-1.0F, -21.0F, 0.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 0).addBox(-3.0F, -21.0F, -2.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 0).addBox(-3.0F, -22.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.0F, -19.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = bone2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, -5.0F, -2.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(6, 4).addBox(0.0F, -5.0F, 1.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(1, 1).addBox(0.0F, -16.0F, -3.0F, 1.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(Joghurt entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		root().getAllParts().forEach(ModelPart::resetPose);
		
		animate(entity.idleAnimationState, JoghurtAnimation.IDLE, ageInTicks);
		
		if(!entity.isInWaterOrBubble())
			animateWalk(JoghurtAnimation.JOGHURT_RUN, limbSwing, 1f, 1f, 1f);
	}
	
	@Override
	public ModelPart root()
	{
		return root;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}