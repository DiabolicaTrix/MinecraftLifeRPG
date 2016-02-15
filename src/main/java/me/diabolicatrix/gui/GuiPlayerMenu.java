package me.diabolicatrix.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.util.ChatComponentText;

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
        this.yPos = ((this.height / 2) - (this.guiHeight / 2)) -16;
        this.buttonList.clear();
        
        this.buttonList.add(new GuiBlackButton(0, this.xPos, this.yPos + guiHeight + 1, 56, 15, "Close"));
        this.buttonList.add(new GuiBlackButton(1, this.xPos + 58, this.yPos + guiHeight + 1, 56, 15, "Settings"));
        this.buttonList.add(new GuiBlackButton(2, this.xPos + 116, this.yPos + guiHeight + 1, 56, 15, "My Gang"));
        this.buttonList.add(new GuiBlackButton(3, this.xPos + 174, this.yPos + guiHeight + 1, 56, 15, "Keychain"));
        this.buttonList.add(new GuiBlackButton(4, this.xPos + 232, this.yPos + guiHeight + 1, 58, 15, "Cellphone"));
        
        this.buttonList.add(new GuiOrangeButton(5, this.xPos + 240, this.yPos + guiHeight - 18, 45, 13, "Give"));
        this.buttonList.add(new GuiOrangeButton(6, this.xPos + 191, this.yPos + guiHeight - 18, 45, 13, "Use"));
        this.buttonList.add(new GuiOrangeButton(7, this.xPos + 142, this.yPos + guiHeight - 18, 45, 13, "Remove"));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        switch(button.id)
        {
            case 0:
                this.mc.displayGuiScreen(null);
                break;
            case 1:
                this.mc.displayGuiScreen(new GuiOptions(this.mc.currentScreen, this.mc.gameSettings));
                break;
            case 2:
                this.mc.displayGuiScreen(null);
                this.mc.thePlayer.addChatComponentMessage(new ChatComponentText("Feature not implemented yet!"));
                break;
            case 3:
                this.mc.displayGuiScreen(null);
                this.mc.thePlayer.addChatComponentMessage(new ChatComponentText("Feature not implemented yet!"));
                break;
            case 4:
                this.mc.displayGuiScreen(null);
                this.mc.thePlayer.addChatComponentMessage(new ChatComponentText("Feature not implemented yet!"));
                break;
        }
    }

}
