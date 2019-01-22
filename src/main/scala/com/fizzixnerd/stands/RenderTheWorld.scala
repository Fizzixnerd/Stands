package com.fizzixnerd.stands

import net.minecraft.client.model.ModelPlayer
import net.minecraft.client.renderer.entity.{RenderLiving, RenderManager}
import net.minecraft.util.ResourceLocation

class RenderTheWorld(renderManager: RenderManager) extends RenderLiving[EntityTheWorld](renderManager, new ModelPlayer(1.0F, false), 0.5F) {
  val texture = new ResourceLocation(Stands.MODID, "textures/entity/the_world.png")

  override
  def getEntityTexture(entity: EntityTheWorld): ResourceLocation = {
    texture
  }
}
