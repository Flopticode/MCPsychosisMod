package com.teamlos.psychosis.client.renderer;

import com.teamlos.psychosis.PsychosisMod;
import com.teamlos.psychosis.client.model.SlothModel;
import com.teamlos.psychosis.entities.Sloth;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SlothRenderer extends MobRenderer<Sloth, SlothModel<Sloth>> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(PsychosisMod.MODID, "textures/entity/sloth.png");
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SlothRenderer(EntityRendererProvider.Context ctx)
	{
		super(ctx, new SlothModel(ctx.bakeLayer(SlothModel.LAYER_LOCATION)), 0.3f);
	}

	@Override
	public ResourceLocation getTextureLocation(Sloth entity)
	{
		return TEXTURE;
	}
	
}
