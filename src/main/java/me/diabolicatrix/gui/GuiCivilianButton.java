package me.diabolicatrix.gui;

import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiCivilianButton extends GuiButton
{
    protected static final ResourceLocation buttonTextures = new ResourceLocation(MinecraftLifeRPG.MODID + ":textures/gui/civilianbtn.png");
    
    public GuiCivilianButton(int buttonId, int x, int y, String buttonText)
    {
        super(buttonId, x, y, 50, 20, buttonText);
    }
    
    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(buttonTextures);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            //int i = this.getHoverState(this.hovered);
            //GlStateManager.enableBlend();
            //GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            //GlStateManager.blendFunc(770, 771);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 50, 20, 50, 20);
            //this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
            //this.mouseDragged(mc, mouseX, mouseY);
            int j = 14737632;

            if (packedFGColour != 0)
            {
                j = packedFGColour;
            }
            else
            if (!this.enabled)
            {
                j = 10526880;
            }
            else if (this.hovered)
            {
                j = 16777120;
            }

            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 16) / 2, j);
            this.drawCenteredString(fontrenderer, "1/4", this.xPosition + this.width / 2, this.yPosition + (this.height + 2) / 2, j);
        }
    }
    
    @Override
    public void playPressSound(SoundHandler soundHandlerIn)
    {
        
    }

}
