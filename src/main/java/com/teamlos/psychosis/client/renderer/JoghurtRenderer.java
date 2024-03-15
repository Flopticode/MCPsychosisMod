package com.teamlos.psychosis.client.renderer;

import com.teamlos.psychosis.PsychosisMod;
import com.teamlos.psychosis.client.model.JoghurtModel;
import com.teamlos.psychosis.entities.Joghurt;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class JoghurtRenderer extends MobRenderer<Joghurt, JoghurtModel<Joghurt>> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(PsychosisMod.MODID, "textures/entity/joghurt.png");
	
	public JoghurtRenderer(EntityRendererProvider.Context ctx)
	{
		super(ctx, new JoghurtModel(ctx.bakeLayer(JoghurtModel.LAYER_LOCATION)), 0.3f);
	}

	@Override
	public ResourceLocation getTextureLocation(Joghurt entity)
	{
		return TEXTURE;
	}
	
}
