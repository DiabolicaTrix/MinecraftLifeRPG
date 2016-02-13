package me.diabolicatrix.entities;

import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import me.diabolicatrix.other.LifeTraderEEP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.DataWatcher;
import net.minecraft.util.ResourceLocation;

public class RenderLifeTrader extends RenderBiped<EntityLifeTrader>
{
    public ResourceLocation locationSkin;
    public ThreadDownloadImageData downloadImageSkin = null;
    public String textureChanged;

    public RenderLifeTrader(RenderManager renderManagerIn, ModelBiped model, float shadow)
    {
        super(renderManagerIn, model, shadow);
    }

    protected ResourceLocation getEntityTexture(EntityLifeTrader living)
    {
        LifeTraderEEP props = LifeTraderEEP.get(living);
        DataWatcher dw = living.getDataWatcher();
        String texture = dw.getWatchableObjectString(20);
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        Object object = texturemanager.getTexture(props.getLocation(texture));
        
        if(props.texture != texture)
        {
            props.downloadImage = null;
        }
        if(props.downloadImage == null)
        {
            props.downloadImage = props.getDownloadImage(props.getLocation(texture), texture);
        }
        props.texture = texture;
        return props.getLocation(texture);
    }

}
