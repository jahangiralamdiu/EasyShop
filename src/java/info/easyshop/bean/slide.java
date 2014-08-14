/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.easyshop.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author BITM
 */
@ManagedBean
@SessionScoped
public class slide implements Serializable {
     private static final long serialVersionUID = 1L;
    private List<String> images;
    
    public slide(){}
    
    @PostConstruct
    
    public void init(){
        images=new ArrayList<String>();
        for(int i=1; i<=9; i++){
            images.add("image"+i+".jpg");
        }
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
    
    
    
}
