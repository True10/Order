package com.sbw.atrue.Order.Util;

import com.sbw.atrue.Order.Entity.Food;
import com.sbw.atrue.Order.Entity.Product;
import com.sbw.atrue.Order.R;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: FoodFctory <br>
 * Description: 用于生成菜单的生产类 <br>
 * Author: 沈滨伟-13042299081 <br>
 * Date: 2019/2/18 22:21
 */
public class FoodFctory {

     static  public Food[] Beef={
             //备注文字如果想要有换行效果的话，直接上\n即可
             new Food("雪花",R.drawable.xuehua,"\t\t\t\t雪花是牛脖子上的凸起的一块肉，是牛运动最为频繁的一块活肉，也是牛身上最好吃的一块肉。一千斤的牛身上，可能也就只有一两斤的雪花牛肉。因为在脖子上，所以它还叫脖仁，脖仁肥瘦相间，拥有大理石般的纹路，涮4-6秒后入口，口感柔嫩多脂，十分鲜甜脆爽。"),
             new Food("匙柄",R.drawable.chibing,"\t\t\t\t匙柄位于牛前腿上方部位，是牛肉中最好的部位之一，即牛夹层里脊肉内层。匙柄的脂肪含量少，切面呈树叶状，且纹理清晰，口感甜嫩无渣。"),
             new Food("匙仁",R.drawable.chiren,"\t\t\t\t匙仁在西餐牛肉的分割部位叫做“眼肉”，该处肉质柔嫩且多汁（有雪花纹），滋味无穷。眼肉属于高档肉，经过精细切割后外观呈四方圆弧状，肉质红白镶嵌，有大理石花斑状纹理。由于臀部的运动较多，眼肉肉质细嫩，脂肪及水分含量较高，吃起来的口感比较香甜多汁、不干涩。"),
             new Food("三花趾",R.drawable.sanhuazhi,"\t\t\t\t如果你在吃潮汕牛肉火锅的时候，雪花脖仁没有了怎么办，虽然有点遗憾，但还有很多部位一样美味，三花趾就是其中的一种。这块牛肉的位置在上肢，就是牛前腿的腱子肉，牛肉中有较粗的筋。"),
             new Food("五花趾",R.drawable.wuhuazhi,"\t\t\t\t五花趾中的筋要比三花趾中的筋还要多一些，肉的纹理也要更加的清晰一些，在口感上也要更加的爽口，吃起来非常的脆。如果你喜欢吃脆弹一些的肉，那么潮汕牛肉火锅中的五花趾就是你的不二之选。"),
             new Food("肥胼",R.drawable.feipin,"\t\t\t\t肥胼对应的部位是大家再熟悉不过的牛腩位置，并且是经过精修的牛腩，只留牛腩腹心及上面一层牛油，这样切开来之后每一片就有明显的两层，一层嫩肉再带着一层黄色的牛油。口感肥腻细嫩，对牛脂香味情有独钟的值得一试。"),
             new Food("吊龙",R.drawable.diaolong,"\t\t\t\t吊龙的意思是牛脊，吊龙伴则是腰脊肉的两个侧边，而这里头还要再细分，吊龙伴中的两个小吊龙，即牛骨盆的夹缝中，两条长长的肉，样子有点像龙虾的两根大触须，潮汕人叫它“伴仔”或“龙虾须”，这几两重的肉须，便是吊龙伴中最为登峰造极的美味，其特点是鲜甜而弹牙。"),
             new Food("嫩肉",R.drawable.nengrou,"\t\t\t\t嫩肉是在牛屁股上的肉，基本上没有油脂，全是精肉，在潮汕牛肉火锅中算是产量比较大的一种了。嫩肉在潮汕牛肉火锅中最大的特点就是甜度高一些。 烫煮时间：8秒 。"),
             new Food("胸口朥",R.drawable.xiongkoulao,"\t\t\t\t胸口朥是牛前胸的脂肪部分，不是每一头牛都有这么一部分肉，只有大而肥的牛才能长出，十分稀有，只有在潮汕火锅才能看到它的身影。它看上去油得出奇，入口却是清甜间微微泛着牛油的香味，口感脆而爽口、带点韧劲，与“肥腻”两字完全不沾边。 烫煮时间：30秒-3分钟 。"),
     };

     static  public List<Product> initDatas() {
          List<Product> productsData = new ArrayList<Product>();
          Product product = new Product(0, "雪花",50, R.drawable.xuehua, 220, "潮汕牛肉", "牛运动最为频繁的一块活肉！", false);
          productsData.add(product);
          product = new Product(1, "匙柄",40, R.drawable.chibing, 340,"潮汕牛肉", "纹理清晰~口感甜嫩无渣！", false);
          productsData.add(product);
          product = new Product(2,"匙仁",52, R.drawable.chiren, 572, "潮汕牛肉", "肉质柔嫩且多汁~滋味无穷！", false);
          productsData.add(product);
          product = new Product(3,"三花趾",38, R.drawable.sanhuazhi, 678, "潮汕牛肉", "牛前腿的腱子肉~", false);
          productsData.add(product);
          product = new Product(4, "五花趾",38, R.drawable.wuhuazhi, 441, "潮汕牛肉", "纹理清晰~肉质爽口！", false);
          productsData.add(product);
          product = new Product(5,"肥胼",42, R.drawable.feipin, 233, "潮汕牛肉", "口感肥腻细嫩，富有牛脂香味~", false);
          productsData.add(product);
          product = new Product(6, "吊龙",40, R.drawable.diaolong, 128, "潮汕牛肉", "鲜甜弹牙~登峰造极的美味！", false);
          productsData.add(product);
          product = new Product(7, "嫩肉",36, R.drawable.nengrou, 214, "潮汕牛肉", "精肉无油~味道甜美！", false);
          productsData.add(product);
          product = new Product(8, "胸口朥",45, R.drawable.xiongkoulao, 82, "潮汕牛肉", "清甜间+牛油的香味~脆而爽口！", false);
          productsData.add(product);
          product = new Product(9, "牛肉丸",38, R.drawable.wanzi, 520, "潮汕牛肉", "纯肉打造！！不可错过！", false);
          productsData.add(product);
          return productsData;
     }
}
