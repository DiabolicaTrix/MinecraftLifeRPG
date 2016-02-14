package me.diabolicatrix.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;

public class GuiPlayerMenu extends GuiMinecraftLife
{
    public int guiWidth = 290;
    public int guiHeight = 150;
    public int xPos;
    public int yPos;

    public GuiPlayerMenu()
    {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawMinecraftLifeBackground(this.xPos, this.yPos, this.xPos + this.guiWidth, this.yPos + this.guiHeight);
        
        this.drawOrangeRect(this.xPos + 5, this.yPos + 16, this.xPos + 95, 11);
        this.drawOrangeRect(this.xPos + 100, this.yPos + 16, this.xPos + 190, 11);
        this.drawOrangeRect(this.xPos + 195, this.yPos + 16, this.xPos + 285, 11);
        
        this.drawBlackRect(this.xPos + 195, this.yPos + 27, this.xPos + 285, this.guiHeight - 50);
        
        this.drawString(fontRendererObj, "Money Stats", (this.xPos + 7), this.yPos + 18, 0xFFFFFF);
        this.drawString(fontRendererObj, "Licenses", (this.xPos + 102), this.yPos + 18, 0xFFFFFF);
        this.drawString(fontRendererObj, "Current Items", (this.xPos + 197), this.yPos + 18, 0xFFFFFF);
        
        this.drawString(fontRendererObj, "Player Menu", this.xPos + 2, this.yPos + 2, 0xFFFFFF);
        this.drawString(fontRendererObj, "Weight: 0/64", (this.xPos + this.guiWidth - 2) - this.fontRendererObj.getStringWidth("Weight: 0/64"), this.yPos + 2, 0xFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    @Override
    public void initGui()
    {
        this.xPos = (this.width / 2) - (this.guiWidth / 2);
        this.yPos = (this.height / 2) - (this.guiHeight / 2);
        this.buttonList.clear();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        
    }

}
