package me.diabolicatrix.other;

import java.awt.image.BufferedImage;
import java.io.File;

import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class LifeTraderEEP implements IExtendedEntityProperties
{

    public final static String EXT_PROP_NAME = "LIFETRADEREEP";

    public ResourceLocation location;
    public String texture;
    public ThreadDownloadImageData downloadImage;

    @Override
    public void saveNBTData(NBTTagCompound compound) {}

    @Override
    public void loadNBTData(NBTTagCompound compound) {}

    @Override
    public void init(Entity entity, World world) {}

    public static LifeTraderEEP get(Entity player)
    {
        return (LifeTraderEEP)player.getExtendedProperties(EXT_PROP_NAME);
    }

    public ThreadDownloadImageData getTextureHat()
    {
        return this.downloadImage;
    }

    public ThreadDownloadImageData getDownloadImage(ResourceLocation resourceLocation, String uuid)
    {
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        Object object = texturemanager.getTexture(resourceLocation);

        if(object == null)
        {
            object = new ThreadDownloadImageData((File)null, String.format("http://127.0.0.1/skins/%s.png", StringUtils.stripControlCodes(uuid)), null, new ImageBufferDownload()
            {
                @Override
                public BufferedImage parseUserSkin(BufferedImage image)
                {
                    return image;
                }
            });
            texturemanager.loadTexture(resourceLocation, (ITextureObject)object);
        }

        return (ThreadDownloadImageData)object;
    }

    public String getTextureInfoUrl(String uuid)
    {
        return String.format("http://127.0.0.1/skins/%s.png", new Object[] {StringUtils.stripControlCodes(uuid)});
    }

    public ResourceLocation getLocation(String uuid)
    {
        return new ResourceLocation(MinecraftLifeRPG.MODID, "textures/entity/skins/" + StringUtils.stripControlCodes(uuid));
    }
}
