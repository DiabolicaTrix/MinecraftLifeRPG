package me.diabolicatrix.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class GuiBlackButton extends GuiButton
{

    public GuiBlackButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText)
    {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
    }
    
    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        FontRenderer fontrenderer = mc.fontRendererObj;
        
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        
        Tessellator tessellator =  Tessellator.getInstance();
        WorldRenderer wr = tessellator.getWorldRenderer();
        
        this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
        
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableAlpha();
        GlStateManager.shadeModel(7425);
        GlStateManager.disableTexture2D();
        
        wr.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        wr.pos(this.xPosition, this.yPosition, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 230).endVertex();
        wr.pos(this.xPosition, this.yPosition + this.height, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 230).endVertex();
        wr.pos(this.xPosition + this.width, this.yPosition + this.height, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 230).endVertex();
        wr.pos(this.xPosition + this.width, this.yPosition, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 230).endVertex();
        tessellator.draw();
        
        GlStateManager.enableTexture2D();
        GlStateManager.shadeModel(7424);
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        
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

        this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + ((this.height / 2) - 3), j);
    }

}
