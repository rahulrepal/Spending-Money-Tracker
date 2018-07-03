package com.spendingmoneytracker;

/**
 * Created by RahulSR on 9/22/2017.
 */

public class Add_Money_Image_Holder {
    public Add_Money_Image_Holder(int imageId) {
        ImageId = imageId;
    }


    public static final Add_Money_Image_Holder image_list[]={
      new Add_Money_Image_Holder(R.drawable.hostelbackground),
            new Add_Money_Image_Holder(R.drawable.entertainmentbackground),
            new Add_Money_Image_Holder(R.drawable.messbackground),
            new Add_Money_Image_Holder(R.drawable.rechargebackground),
            new Add_Money_Image_Holder(R.drawable.wifibackground),
            new Add_Money_Image_Holder(R.drawable.transportation),
            new Add_Money_Image_Holder(R.drawable.drinksbackgroung),
            new Add_Money_Image_Holder(R.drawable.shoppingbackground),
            new Add_Money_Image_Holder(R.drawable.utilitiesbackground),
            new Add_Money_Image_Holder(R.drawable.utilitiesbackground)
    };
    public int getImageId() {
        return ImageId;
    }

    int ImageId;
}
