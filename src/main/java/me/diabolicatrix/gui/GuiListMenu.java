package me.diabolicatrix.gui;

import java.io.IOException;
import java.util.List;

import com.google.common.collect.Lists;

import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class GuiListMenu extends GuiUtils
{
    private List<String> elementList;
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private int stringID = 0;
    
    private ResourceLocation btnTexture = new ResourceLocation(MinecraftLifeRPG.MODID + ":textures/gui/widgets.png");
    
    public GuiListMenu(List list, int x, int y, int width)
    {
        this.xPos = x;
        this.yPos = y;
        this.width = width;
        this.height = 23;
        this.elementList = list;
    }
    
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        int btnPosX = this.xPos + this.width - 21 - 1;
        int btnWidth = 21;
        int btnHeigth = 21;
        int btnPosY = this.yPos + 1;
        
        boolean upClicked = mouseX >= btnPosX && mouseY >= btnPosY && mouseX < btnPosX + btnWidth && mouseY < btnPosY + 11;
        boolean downClicked = mouseX >= btnPosX && mouseY >= (btnPosY + 11) && mouseX < btnPosX + btnWidth && mouseY < (btnPosY + 21);
        
        if(upClicked)
        {
            int nextStringID = this.stringID + 1;
            if(nextStringID <= (this.elementList.size() - 1) && nextStringID >= 0)
            {
                this.stringID++;
            }
        }
        else if (downClicked)
        {
            int nextStringID = this.stringID - 1;
            if(nextStringID <= (this.elementList.size() - 1) && nextStringID >= 0)
            {
                this.stringID--;
            }
        }
    }
    
    public void drawList(Minecraft mc, int mouseX, int mouseY)
    {
        
        Tessellator tessellator =  Tessellator.getInstance();
        WorldRenderer wr = tessellator.getWorldRenderer();
        
        this.startDrawing();
        
        wr.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        wr.pos(this.xPos, this.yPos, 0.0D).tex(0.0D, 1.0D).color(140, 119, 119, 255).endVertex();
        wr.pos(this.xPos, this.yPos + this.height, 0.0D).tex(1.0D, 1.0D).color(140, 119, 119, 255).endVertex();
        wr.pos(this.xPos + this.width, this.yPos + this.height, 0.0D).tex(1.0D, 0.0D).color(140, 119, 119, 255).endVertex();
        wr.pos(this.xPos + this.width, this.yPos, 0.0D).tex(0.0D, 0.0D).color(140, 119, 119, 255).endVertex();
        tessellator.draw();
        
        wr.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        wr.pos(this.xPos + 1, this.yPos + 1, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255).endVertex();
        wr.pos(this.xPos + 1, this.yPos + this.height - 1, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
        wr.pos(this.xPos + this.width - 1, this.yPos + this.height - 1, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255).endVertex();
        wr.pos(this.xPos + this.width - 1, this.yPos + 1, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
        tessellator.draw();
        
        wr.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        wr.pos(this.xPos + this.width - 15, this.yPos + 1, 0.0D).tex(0.0D, 1.0D).color(47, 28, 28, 255).endVertex();
        wr.pos(this.xPos + this.width - 15, this.yPos + this.height - 1, 0.0D).tex(1.0D, 1.0D).color(47, 28, 28, 255).endVertex();
        wr.pos(this.xPos + this.width - 1, this.yPos + this.height - 1, 0.0D).tex(1.0D, 0.0D).color(47, 28, 28, 255).endVertex();
        wr.pos(this.xPos + this.width - 1, this.yPos + 1, 0.0D).tex(0.0D, 0.0D).color(47, 28, 28, 255).endVertex();
        tessellator.draw();

        
        /*
         * End Draw
         */
        
        this.stopDrawing();
        
        int btnPosX = this.xPos + this.width - 21 - 1;
        int btnWidth = 21;
        int btnHeigth = 21;
        int btnPosY = this.yPos + 1;
        
        Minecraft.getMinecraft().getTextureManager().bindTexture(btnTexture);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        boolean upHovered = mouseX >= btnPosX && mouseY >= btnPosY && mouseX < btnPosX + btnWidth && mouseY < btnPosY + 11;
        boolean downHovered = mouseX >= btnPosX && mouseY >= (btnPosY + 11) && mouseX < btnPosX + btnWidth && mouseY < (btnPosY + 21);
        int uvX = 0, uvY = 0, uvX2 = 0, uvY2 = 11;
        
        if(upHovered)
        {
            uvY = 21;
        }
        else if(downHovered)
        {
            uvY2 = 32;
        }
        
        this.drawTexturedModalRect(btnPosX, btnPosY, uvX, uvY, btnWidth, 11);
        this.drawTexturedModalRect(btnPosX, btnPosY + 11, uvX2, uvY2, btnWidth, 10);
        
        /*
         * Draw String
         */
        this.drawString(mc.fontRendererObj, this.getListString(stringID), this.xPos + 3, this.yPos + (this.height / 2) - (mc.fontRendererObj.FONT_HEIGHT / 2), 0xFFFFFF);

        
    }
    
    public String getListString(int id)
    {
        return this.elementList.size() == 0 ? "" : id <= (this.elementList.size() - 1) ? this.elementList.get(id) : "";
    }
}
