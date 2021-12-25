
package com.gf.modelo.entidades;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author victor
 */
public class Images {
    
    private ImageIcon imagen;

    public Images(Blob b) {
        
        imagen=transformBlob(b);
    }
    
    private ImageIcon transformBlob(Blob blob){
        
        
        try {
            byte[] data = blob.getBytes(1, (int)blob.length()); 
            
            BufferedImage bi = ImageIO.read(new ByteArrayInputStream(data));
            
            return new ImageIcon(bi);
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;
        
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


}
