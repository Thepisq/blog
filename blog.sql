﻿# Host: 127.0.0.1  (Version 5.7.25)
# Date: 2020-08-06 21:50:33
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "blog"
#

DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `author_id` bigint(20) NOT NULL DEFAULT '0',
  `title` varchar(30) NOT NULL,
  `content` mediumtext NOT NULL,
  `likes` int(11) NOT NULL DEFAULT '0',
  `clicks` int(11) NOT NULL DEFAULT '0',
  `collects` int(11) NOT NULL DEFAULT '0',
  `comments` int(11) NOT NULL DEFAULT '0',
  `first_push_date` datetime DEFAULT NULL,
  `last_push_date` datetime DEFAULT NULL,
  `topic_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "blog"
#

INSERT INTO `blog` VALUES (1287006890502557696,16,'厄尔尼诺现象','<p>\t厄尔尼诺现象（又称厄尔尼诺暖流），是<a href=\"https://baike.baidu.com/item/%E5%A4%AA%E5%B9%B3%E6%B4%8B/118304\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">太平洋</a>一种反常的自然现象，在<a href=\"https://baike.baidu.com/item/%E5%8D%97%E7%BE%8E%E6%B4%B2/138913\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">南美洲</a>西海岸、<a href=\"https://baike.baidu.com/item/%E5%8D%97%E5%A4%AA%E5%B9%B3%E6%B4%8B/69193\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">南太平洋</a>东部，自南向北流动着一股著名的<a href=\"https://baike.baidu.com/item/%E7%A7%98%E9%B2%81%E5%AF%92%E6%B5%81/5062443\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">秘鲁寒流</a>，每年的11月至次年的3月正是<a href=\"https://baike.baidu.com/item/%E5%8D%97%E5%8D%8A%E7%90%83/1696241\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">南半球</a>的夏季，南半球海域水温普遍升高，向西流动的<a href=\"https://baike.baidu.com/item/%E8%B5%A4%E9%81%93\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">赤道</a>暖流得到加强。恰逢此时，全球的气压带和风带向南移动，<a href=\"https://baike.baidu.com/item/%E4%B8%9C%E5%8C%97%E4%BF%A1%E9%A3%8E/1967427\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">东北信风</a>越过赤道受到南半球自偏向力（也称<a href=\"https://baike.baidu.com/item/%E5%9C%B0%E8%BD%AC%E5%81%8F%E5%90%91%E5%8A%9B/4924727\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">地转偏向力</a>）的作用，向左偏转成西北季风。西北季风不但削弱了秘鲁西海岸的<a href=\"https://baike.baidu.com/item/%E7%A6%BB%E5%B2%B8%E9%A3%8E/2118858\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">离岸风</a>——<a href=\"https://baike.baidu.com/item/%E4%B8%9C%E5%8D%97%E4%BF%A1%E9%A3%8E/1968341\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">东南信风</a>，使秘鲁寒流冷水上泛减弱甚至消失，而且吹拂着水温较高的赤道暖流南下，使秘鲁寒流的水温反常升高。这股悄然而至、不固定的洋流被称为“厄尔尼诺暖流”。</p><p>\t厄尔尼诺又分为<a href=\"https://baike.baidu.com/item/%E5%8E%84%E5%B0%94%E5%B0%BC%E8%AF%BA%E7%8E%B0%E8%B1%A1/98796\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">厄尔尼诺现象</a>和<a href=\"https://baike.baidu.com/item/%E5%8E%84%E5%B0%94%E5%B0%BC%E8%AF%BA%E4%BA%8B%E4%BB%B6/3363375\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">厄尔尼诺事件</a>。厄尔尼诺现象是发生在热带太平洋海温异常增暖的一种气候现象，大范围热带太平洋增暖，会造成全球气候的变化，但这个状态要维持3个月以上，才认定是真正发生了<a href=\"https://baike.baidu.com/item/%E5%8E%84%E5%B0%94%E5%B0%BC%E8%AF%BA%E4%BA%8B%E4%BB%B6/3363375\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">厄尔尼诺事件</a>。在厄尔尼诺现象发生后，<a href=\"https://baike.baidu.com/item/%E6%8B%89%E5%B0%BC%E5%A8%9C%E7%8E%B0%E8%B1%A1/515544\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">拉尼娜现象</a>有时会紧随其后。</p><p>\t然而，根据2015年12月27日美国国家航空暨太空总署(<a href=\"https://baike.baidu.com/item/NASA\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">NASA</a>)新发布的卫星影像显示，太平洋上厄尔尼诺现象没有转弱迹象。专家预测2016年可能成为厄尔尼诺破坏力最大的一年。</p><p>\t2020年5月8日，国家气候中心监测显示，一次弱厄尔尼诺事件已形成，预计今夏涝重于旱。</p>',0,0,0,0,'2020-07-25 00:00:00','2020-07-25 00:00:00',1),(1287007590838079488,16,'鱼鳞云','<p>\t鱼鳞云，又叫透光<a href=\"https://baike.baidu.com/item/%E9%AB%98%E7%A7%AF%E4%BA%91\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">高积云</a>（Altocumulus translucidus），形似<a href=\"https://baike.baidu.com/item/%E9%B1%BC%E9%B3%9E/1124365\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">鱼鳞</a>的白<a href=\"https://baike.baidu.com/item/%E4%BA%91/53706\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">云</a>，一排排一列列，是强<a href=\"https://baike.baidu.com/item/%E5%86%B7%E7%A9%BA%E6%B0%94/6138619\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">冷空气</a>到来时<a href=\"https://baike.baidu.com/item/%E5%87%BA%E7%8E%B0/1921361\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">出现</a>的一种<a href=\"https://baike.baidu.com/item/%E4%BA%91%E5%B1%82/6417493\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">云层</a>，<a href=\"https://baike.baidu.com/item/%E9%A2%84%E7%A4%BA/805151\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">预示</a>着近期<a href=\"https://baike.baidu.com/item/%E5%A4%A9%E6%B0%94%E7%8A%B6%E5%86%B5\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">天气状况</a>不稳定。</p><p>\t透光高积云云块较薄，呈<a href=\"https://baike.baidu.com/item/%E7%99%BD%E8%89%B2/80367\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">白色</a>，常成一个或两个方向<a href=\"https://baike.baidu.com/item/%E6%95%B4%E9%BD%90/2082505\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">整齐</a>地排列，云块之间有<a href=\"https://baike.baidu.com/item/%E6%98%8E%E6%98%BE/10801689\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">明显</a>的<a href=\"https://baike.baidu.com/item/%E7%BC%9D%E9%9A%99/11008720\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">缝隙</a>，即使无<a href=\"https://baike.baidu.com/item/%E7%BC%9D%E9%9A%99/11008720\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">缝隙</a>云块<a href=\"https://baike.baidu.com/item/%E8%BE%B9%E7%BC%98/32781\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">边缘</a>也较<a href=\"https://baike.baidu.com/item/%E6%98%8E%E4%BA%AE/23922\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">明亮</a>。</p><p>\t<a href=\"https://baike.baidu.com/item/%E9%B1%BC%E9%B3%9E%E4%BA%91\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">鱼鳞云</a>是指云间有规则的、<a href=\"https://baike.baidu.com/item/%E7%A9%BA%E9%9A%99/2456983\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">空隙</a>大的大范围云层。出现这种云层是天气将转晴并且有两三天<a href=\"https://baike.baidu.com/item/%E4%B8%87%E9%87%8C%E6%99%B4%E7%A9%BA\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">万里晴空</a>的征兆。<a href=\"https://baike.baidu.com/item/%E5%8E%9F%E5%9B%A0/32935\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">原因</a>是高空<a href=\"https://baike.baidu.com/item/%E5%86%B7%E7%A9%BA%E6%B0%94/6138619\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">冷空气</a>下沉冲破了连续的<a href=\"https://baike.baidu.com/item/%E4%BA%91%E5%B1%82\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">云层</a>，从而形成了鱼鳞云。冷空气下沉到地面受热，<a href=\"https://baike.baidu.com/item/%E7%A9%BA%E6%B0%94/2735809\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">空气</a>中的水分蒸发，<a href=\"https://baike.baidu.com/item/%E7%9B%B8%E5%AF%B9%E6%B9%BF%E5%BA%A6\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">相对湿度</a>降低，<a href=\"https://baike.baidu.com/item/%E6%B0%94%E5%8E%8B\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">气压</a>升高，<a href=\"https://baike.baidu.com/item/%E4%BA%91%E5%B1%82\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">云层</a>也升高（<a href=\"https://baike.baidu.com/item/%E5%85%B3%E9%94%AE%E8%AF%8D/254446\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">关键词</a>：云层<a href=\"https://baike.baidu.com/item/%E6%82%AC%E6%B5%AE/1130648\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">悬浮</a>在等密度的流体中）从而出现连续晴天。这样的<a href=\"https://baike.baidu.com/item/%E4%BA%91%E5%B1%82\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">云层</a>一般出现在<a href=\"https://baike.baidu.com/item/%E7%A7%8B%E5%A4%A9\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">秋天</a>。 也即将出现秋高气爽的征兆。</p><p>\t漂浮在天空中的云彩是由许多细小的<a href=\"https://baike.baidu.com/item/%E6%B0%B4%E6%BB%B4/9086002\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">水滴</a>或<a href=\"https://baike.baidu.com/item/%E5%86%B0%E6%99%B6/1151253\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">冰晶</a>组成的，有的是由小水滴或小<a href=\"https://baike.baidu.com/item/%E5%86%B0%E6%99%B6/1151253\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">冰晶</a>混合在一起组成的。有时也包含一些较大的雨滴及冰、雪粒，云的底部不接触地面，并有一定<a href=\"https://baike.baidu.com/item/%E5%8E%9A%E5%BA%A6/8739360\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">厚度</a>。</p><p>\t从地面向上十几公里这层大气中，越靠近<a href=\"https://baike.baidu.com/item/%E5%9C%B0%E9%9D%A2/33141\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">地面</a>，<a href=\"https://baike.baidu.com/item/%E6%B8%A9%E5%BA%A6/221599\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">温度</a>越高，空气也越稠密；越往高空，温度越低，空气也越稀薄。</p><p>\t另一方面，江河湖海的水面，以及土壤和动、<a href=\"https://baike.baidu.com/item/%E6%A4%8D%E7%89%A9/142914\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">植物</a>的水分，随时<a href=\"https://baike.baidu.com/item/%E8%92%B8%E5%8F%91/53556\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">蒸发</a>到空中变成<a href=\"https://baike.baidu.com/item/%E6%B0%B4%E6%B1%BD\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">水汽</a>。水汽进入大气后，成云致雨，或<a href=\"https://baike.baidu.com/item/%E5%87%9D%E8%81%9A/7977449\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">凝聚</a>为霜露，然后又返回地面，渗入土壤或流入江河湖海。以后又再蒸发(升华)，再凝结(<a href=\"https://baike.baidu.com/item/%E5%87%9D%E5%8D%8E\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">凝华</a>)下降。周而复始，循环不已。水汽从蒸发表面进入<a href=\"https://baike.baidu.com/item/%E4%BD%8E%E5%B1%82%E5%A4%A7%E6%B0%94\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">低层大气</a>后，这里的温度高，所容纳的水汽较多，如果这些湿热的空气被抬升，温度就会逐渐降低，到了一定<a href=\"https://baike.baidu.com/item/%E9%AB%98%E5%BA%A6/6108396\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">高度</a>，<a href=\"https://baike.baidu.com/item/%E7%A9%BA%E6%B0%94/2735809\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">空气</a>中的水汽就会达到饱和。如果空气继续被抬升，就会有多余的水汽析出。如果那里的温度高于0℃，则多余的水汽就凝结成小水滴；如果温度低于0℃，则多余的水汽就凝化为小冰晶。在这些小水滴和小冰晶逐渐增多并达到人眼能辨认的程度时，就是云了。</p><p>\t透光<a href=\"https://baike.baidu.com/item/%E9%AB%98%E7%A7%AF%E4%BA%91\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">高积云</a>，一般而言，<a href=\"https://baike.baidu.com/item/%E5%86%B7%E7%A9%BA%E6%B0%94\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">冷空气</a>来之前，<a href=\"https://baike.baidu.com/item/%E4%BA%91%E5%B1%82\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">云层</a>逐渐加厚，并呈层分布，厚重的云层在相互“<a href=\"https://baike.baidu.com/item/%E6%8C%A4%E5%8E%8B\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">挤压</a>”下，就会呈现出<a href=\"https://baike.baidu.com/item/%E9%B1%BC%E9%B3%9E\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">鱼鳞</a>形状的云。气象<a href=\"https://baike.baidu.com/item/%E4%B8%93%E5%AE%B6/3369419\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">专家</a>介绍，这种<a href=\"https://baike.baidu.com/item/%E9%B1%BC%E9%B3%9E%E4%BA%91\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">鱼鳞云</a>累积到一定程度就会变为降雨云团。<a href=\"https://baike.baidu.com/item/%E6%B0%94%E8%B1%A1%E8%B0%9A%E8%AF%AD\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">气象谚语</a>形象地说：“鱼鳞天，不雨也风颠”。</p><p>\t透光<a href=\"https://baike.baidu.com/item/%E9%AB%98%E7%A7%AF%E4%BA%91\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">高积云</a>的特点是<a href=\"https://baike.baidu.com/item/%E4%BA%91%E5%B1%82\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">云层</a>处于比较高的高空，云块较薄，呈白色，常成一个或两个方向<a href=\"https://baike.baidu.com/item/%E6%95%B4%E9%BD%90\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">整齐</a>地排列，云块之间有明显的<a href=\"https://baike.baidu.com/item/%E7%BC%9D%E9%9A%99\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">缝隙</a>，即使无缝隙云块边缘也较明亮，能辨别<a href=\"https://baike.baidu.com/item/%E6%97%A5/35262\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">日</a>、月位置。</p><p>\t为波状云，云块轮廓分明，常呈扁圆形、瓦块状、<a href=\"https://baike.baidu.com/item/%E9%B1%BC%E9%B3%9E%E7%89%87\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">鱼鳞片</a>，或是水波状的密集云条。成群、成行、成波状排列。大多数云块的视宽度角在1-5度。有时可出现在两个或几个高度上。薄的云块成白色，厚的云块呈暗灰色。在薄的<a href=\"https://baike.baidu.com/item/%E9%AB%98%E7%A7%AF%E4%BA%91\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">高积云</a>上，常有环绕日月的<a href=\"https://baike.baidu.com/item/%E8%99%B9%E5%BD%A9\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">虹彩</a>，或颜色为外红内蓝的华环。\t透光高积云如果变化不大，预示着晴天。如果<a href=\"https://baike.baidu.com/item/%E9%AB%98%E7%A7%AF%E4%BA%91\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">高积云</a>厚度继续增厚，并逐渐融合成层，则显示天气将有变化，甚至会出现降水。<span style=\"color: rgb(108, 162, 218); --darkreader-inline-color:#6ca9da;\" data-darkreader-inline-color=\"\">&nbsp;</span></p><p>\t民间有“天现鱼鳞云，不雨风也颠”的说法，出现这种云彩，是冷空气到来前，云层逐渐压低、加厚，由于高层气流不稳定，形成“鱼鳞云”，之后往往会下阵雨。</p>',0,0,0,0,'2020-07-25 00:00:00','2020-07-25 00:00:00',1),(1287011942067769344,16,'火彩虹','<p>\t环地平弧又称日载或日承现象。民间也有<a href=\"https://baike.baidu.com/item/%E5%BD%A9%E8%99%B9%E4%BA%91\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">彩虹云</a>和火焰彩虹的说法。环地平弧号称为所有晕像中最美丽者，其<a href=\"https://baike.baidu.com/item/%E9%A2%9C%E8%89%B2/5014\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">颜色</a>顺序自上而下分别为：红色、<a href=\"https://baike.baidu.com/item/%E6%A9%99%E8%89%B2/2786334\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">橙色</a>、黄色、<a href=\"https://baike.baidu.com/item/%E7%BB%BF%E8%89%B2/449467\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">绿色</a>、<a href=\"https://baike.baidu.com/item/%E8%93%9D%E8%89%B2/35972\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">蓝色</a>、靛蓝、蓝紫色。是光的一种。</p><p>\t环地平弧必须在<a href=\"https://baike.baidu.com/item/%E5%A4%AA%E9%98%B3/24010\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">太阳</a>距离<a href=\"https://baike.baidu.com/item/%E5%9C%B0%E5%B9%B3%E7%BA%BF/68347\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">地平线</a>至少58<a href=\"https://baike.baidu.com/item/%E5%BA%A6/5542\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">度</a>时才会出现，但在<a href=\"https://baike.baidu.com/item/%E4%B8%AD%E7%BA%AC%E5%BA%A6/5854042\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">中纬度</a>地区，<a href=\"https://baike.baidu.com/item/%E5%A4%AA%E9%98%B3/24010\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">太阳</a>仅在六月和七月初才能到达此一高度，且仅限于日中前后数小时内。</p><p>\t环地平弧<a href=\"https://baike.baidu.com/item/%E7%8E%B0%E8%B1%A1/2808631\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">现象</a>的形成原理与<a href=\"https://baike.baidu.com/item/%E7%8E%AF%E5%A4%A9%E9%A1%B6%E5%BC%A7/6403441\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">环天顶弧</a>相似，均为太阳光照射<a href=\"https://baike.baidu.com/item/%E9%AB%98%E5%B1%82%E5%A4%A7%E6%B0%94/849123\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">高层大气</a>中冰晶<a href=\"https://baike.baidu.com/item/%E6%8A%98%E5%B0%84/8433011\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">折射</a>产生所产生。不同的是，环地平弧现象的入射光从冰晶的上面进入，因此<a href=\"https://baike.baidu.com/item/%E6%8A%98%E5%B0%84/8433011\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">折射</a>出来的颜色<a href=\"https://baike.baidu.com/item/%E9%A1%BA%E5%BA%8F/10959322\" target=\"_blank\" style=\"color: rgb(99, 178, 240); --darkreader-inline-color:#63b4f0;\" data-darkreader-inline-color=\"\">顺序</a>与环天顶弧相反。</p>',0,0,0,0,'2020-07-25 00:00:00','2020-07-25 00:00:00',1),(1289140385257504768,1289131682022182912,'putty提供的两个文件传输工具PSCP、PSFTP详细介绍','<p>用 SSH 来传输文件</p><p>PuTTY 提供了两个文件传输工具</p><ul><li>PSCP (PuTTY Secure Copy client)</li><li>PSFTP (PuTTY SFTP client)</li></ul><p>PSCP 通过 SSH 连接，在两台机器之间安全的传输文件，可以用于任何 SSH（包括 SSH v1、SSH v2） 服务器。</p><p>PSFTP 则是 SSH-2 中新增的特性，使用的是新的 SFTP 协议，使用上与传统的 FTP 类似。事实上 PSCP 如果发现 SFTP 可用，PSCP就会使用 SFTP 协议来传输文件，否则还是 SCP 协议。PSFTP 与 PSCP 相比，PSFTP 的优点是可以与服务器进行交互，遍历服务器上的文件系统，在一个会话中上传或下载多个文件。而 PSCP 只能一次传输一个文件，传输完毕后立刻终止会话。</p><p>PSCP 的使用</p><p>在控制台直接执行 pscp 可以看到帮助</p><p>所以 PSCP 大致用法的例子就是：</p><p>pscp -P 22 -i c:\\path\\your-private-key.ppk -C username@server:/remote/path/</p><p>下面还是用一些实例来说明会比较简单一些：</p><p>把本地的 C:\\path\\foo.txt 复制到远程主机 192.168.6.200 的 /tmp 目录下</p><blockquote>pscp c:\\path\\foo.txt 192.168.6.200:/tmp</blockquote><p>把本地的 C:\\path\\foo.txt 复制到主机 192.168.6.200 的 /tmp 目录下，但是以主机上的用户 taylor 的权限执行</p><blockquote>pscp c:\\path\\foo.txt taylor@192.168.6.200:/tmp</blockquote><p>或者是</p><blockquote>pscp -l taylor c:\\path\\foo.txt 192.168.6.200:/tmp</blockquote><p>把本地的 C:\\path\\foo.txt 传送到主机 192.168.6.200 的 /tmp 目录下，但是主机的 SSH 端口是 3122</p><blockquote>pscp -P 3122 c:\\path\\foo.txt 192.168.6.200:/tmp</blockquote><p>把本地的 C:\\path\\foo.txt 复制到主机 192.168.6.200 的用户 taylor 的主目录下</p><blockquote>pscp c:\\path\\foo.txt taylor@192.168.6.200:.</blockquote><p>把主机 192.168.6.200 上的用户 taylor 主目录下的所有 *.tgz 文件拷贝到本地的 c:\\backup 目录中，如果 SSH 版本是 SSH v1，那这个命令就会出错。</p><blockquote>pscp taylor@192.168.6.200:*.tgz c:\\backup</blockquote><p><span style=\"color: rgb(204, 201, 193); --darkreader-inline-color:#ccc8c1;\" data-darkreader-inline-color=\"\">再来看看 PSFTP</span></p><p>用法与 PSCP 大同小异，虽然有个 -load 选项，其实这个没啥用，后面用主机名的时候，与 PSCP 一样直接用上会话名称就可以了。</p><p>用 PSFTP 登录到服务器上以后，操作与 FTP 差不多，这里简单的说一下吧：</p><ul><li>open 登录主机</li><li>open [username@]&lt;sessname|hostname|ip&gt; [port]</li><li>比如：</li><li>open taylor@demo-server 3022</li><li class=\"ql-indent-1\">就是以用户 taylor 的身份，登陆到主机 demo-server 上，SSH 端口是 3022</li><li>open demo-server</li><li class=\"ql-indent-1\">登陆 demo-server，这里的 demo-server 可以是PuTTY 中已经保存的会话名称，也可以是主机的名称，如果主机名称与会话名称相同，以会话名称为准。</li><li>close 关闭 SFTP 连接</li><li>这个没啥说的，close 就关闭了 SFTP 连接</li><li>quit 结束本次的 SFTP 会话</li><li>也没啥用法，就是关闭了 PSFTP 这个程序</li><li>help [command] 帮助</li><li>直接打 help 就可以看到帮助指令，后面指定上 一个命令就可以查看该命令的帮助，比如： help open</li><li>cd [directory] 改变当前目录</li><li>pwd 察看当前目录</li><li>lcd [directory] 改变本地目录</li><li>lpwd 察看本地当前目录</li><li>get [-r] &lt;filename|directory&gt; 从服务器下载一个文件/目录，这个命令不能用通配符，参数 -r 可以递归下载整个目录</li><li>put [-r] &lt;filename|directory&gt; [dest] 把文件/目录上传到服务器，这个命令不能用通配符，参数 -r 可以递归上传整个目录</li><li>mget [-r] &lt;filename|directory&gt; 从服务器下载一批文件/目录，可以用通配符，-r 的含义与 get 一样</li><li>mget [-r] &lt;filename|directory&gt; [dest] 把一批文件/目录上传到服务器，可以用通配符，-r 的含义与 put 一样</li><li>reget [-r] &lt;filename|directory&gt; 从服务器续传下载一个文件/目录，这个命令不能用通配符，-r 的含义与 get 一样</li><li>reput [-r] &lt;filename|directory&gt; [dest] 把一批文件/目录续传上传到服务器，这个命令不能用通配符，-r 的含义与 put 一样</li><li>dir [directory] 列目录</li><li>ls 和 dir 一样</li><li>chmod [file|directory] 改变文件的权限，与 Unix 的 chmod 命令类似</li><li>del &lt;filename&gt; 删除文件，要注意的是 del 只能删除文件</li><li>rm 与 del 一样</li><li>mkdir &lt;new-directory-name&gt; 创建一个目录</li><li>rmdir &lt;directory&gt; 删除一个空目录，只有空目录才可以被删除</li><li>mv &lt;source-file|source-directory&gt; &lt;dest-file|dest-directory&gt; 改名/移动。如果源和目的都是文件或目录，则是改名。如果目的是目录的话，则是移动。</li><li>! 在本地命令前加一个感叹号，就可以直接执行</li></ul><p><br></p>',0,0,0,0,'2020-07-31 10:06:34','2020-07-31 10:06:34',1),(1289155109902811136,1289154850527051776,'SpringBoot集成redis缓存设置','<p>Remote DIctionary Server(Redis) 是一个由Salvatore Sanfilippo写的key-value存储系统。</p><p>Redis是一个开源的使用ANSI C语言编写、遵守BSD协议、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。它通常被称为数据结构服务器，因为值（value）可以是 字符串(String), 哈希(Map), 列表(list), 集合(sets) 和 有序集合(sorted sets)等类型。</p><p>&nbsp;</p><p>SpringBoot为Redis提供了良好的支持，可以非常方便的配置Redis缓存环境，下面开始配置并测试。</p><h3><strong>引入依赖</strong></h3><p>主要依赖spring-boot-starter-data-redis，其后面自动依赖其他框架。</p><h3><strong>自动引入的依赖</strong></h3><p class=\"ql-align-center\"><img src=\"https://www.zhoutao123.com/wp-content/uploads/2018/03/6df013d58a92bf451cd90aca0515a81e.png\"></p><h3><strong>POM配置文件</strong></h3><pre class=\"ql-syntax\" spellcheck=\"false\">\n        &lt;!--Redis--&gt;\n\n        &lt;dependency&gt;\n\n            &lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;\n\n            &lt;artifactId&gt;spring-boot-starter-data-redis&lt;/artifactId&gt;\n\n        &lt;/dependency&gt;\n</pre><p>&nbsp;</p><h3><strong>配置yml文件</strong></h3><p>由于SpringBoot采用了大部分的默认配置，因此一些默认配置我们可以省略不写，这里为了演示，我们写出来。</p><pre class=\"ql-syntax\" spellcheck=\"false\">\nspring:\n\n  #配置Redis数据\n\n  redis:\n\n    # 使用的数据库，默认为0\n\n    database: 1\n\n    # host主机，默认为localhost\n\n    host: 127.0.0.1\n\n    # 端口号,默认为6379\n\n    port: 6379\n\n    # 密码，默认为空\n\n    password:\n\n \n</pre><h3><strong>使用Redis作为缓存</strong></h3><p>使用之前需要注入一个Redis模板，这里我们使用<code>StringRedisTemplate</code>来注入，Redis主要有两个Template，具体的内容可以参看这篇文章&nbsp;<a href=\"https://blog.csdn.net/notsaltedfish/article/details/75948281\" target=\"_blank\" style=\"color: rgb(103, 149, 181); --darkreader-inline-color:#8cafc7;\" data-darkreader-inline-color=\"\">关于RedisTemplate和StringRedisTemplate</a></p><pre class=\"ql-syntax\" spellcheck=\"false\">\n    @GetMapping(\"/redis\")\n\n    public String redisExample(){\n\n        //从Redis中，通过Key获取信息\n\n        String redisExample = stringRedisTemplate.opsForValue().get(\"redisExample\");\n\n        //如果不存在，那么更新，并且从新设置到Redis中\n\n        if (redisExample == null){\n\n            String date = LocalDateTime.now().toString();\n\n            redisExample = date;\n\n            System.out.println(\"Redis缓存时间已经更新为\"+redisExample);\n\n            //设置参数并更新过期时间，过期时间为10,单位为秒\n\n            stringRedisTemplate.opsForValue().set(\"redisExample\",redisExample,10, TimeUnit.SECONDS);\n\n        }\n\n        return redisExample;\n\n    }\n\n \n</pre><p>&nbsp;</p><h2><strong>使用工具查看Reis数据</strong></h2><p>Redis Desktop Manage是一个Redis的图像化界面数据，他可以直接读取到Redis的数据，方便我们查看数据和调试。</p><p>在我们设置了Redis缓存之后，可以看到Redis Desktop Manage中已经保存了数据。</p><p class=\"ql-align-center\"><img src=\"https://www.zhoutao123.com/wp-content/uploads/2018/03/b42fec24ea37371372960d620d49c658.png\"></p><p><br></p><p>至此，SpringBoot集成Redis完成，相对而言，比SSM框架简单了很多。</p>',0,0,0,0,'2020-07-31 11:05:04','2020-07-31 11:05:04',1),(1289155660015140864,1289154850527051776,'2020年7月31日19:07:09','<p>2020年7月31日19:07:09</p>',0,0,0,0,'2020-07-31 11:07:15','2020-07-31 20:04:10',1),(1289990349760126976,1289987687325032448,'回调函数','<p>如果要处理 $.ajax() 得到的数据，则需要使用回调函数：beforeSend、error、dataFilter、success、complete。</p><h3><strong>beforeSend</strong></h3><p>在发送请求之前调用，并且传入一个 XMLHttpRequest 作为参数。</p><h3><strong>error</strong></h3><p>在请求出错时调用。传入 XMLHttpRequest 对象，描述错误类型的字符串以及一个异常对象（如果有的话）</p><h3><strong>dataFilter</strong></h3><p>在请求成功之后调用。传入返回的数据以及 \"dataType\" 参数的值。并且必须返回新的数据（可能是处理过的）传递给 success 回调函数。</p><h3><strong>success</strong></h3><p>当请求之后调用。传入返回后的数据，以及包含成功代码的字符串。</p><h3><strong>complete</strong></h3><p>当请求完成之后调用这个函数，无论成功或失败。传入 XMLHttpRequest 对象，以及一个包含成功或错误代码的字符串。</p>',0,0,0,0,'2020-08-03 02:24:01','2020-08-03 02:24:01',1),(1290675019615211520,1289154850527051776,'jQuery 效果 - 隐藏和显示','<h2><strong>jQuery hide() 和 show()</strong></h2><p><br></p><p>通过 jQuery，您可以使用 hide() 和 show() 方法来隐藏和显示 HTML 元素：</p><pre class=\"ql-syntax\" spellcheck=\"false\">$(\"#hide\").click(function(){\n  $(\"p\").hide();\n});\n\n$(\"#show\").click(function(){\n  $(\"p\").show();\n});\n</pre><p><br></p>',0,0,0,0,'2020-08-04 23:44:39','2020-08-04 23:44:39',3),(1290675218941120512,1289154850527051776,'jQuery prop() 方法','<h2>定义和用法</h2><p>prop() 方法设置或返回被选元素的属性和值。</p><p>当该方法用于<strong>返回</strong>属性值时，则返回第一个匹配元素的值。</p><p>当该方法用于<strong>设置</strong>属性值时，则为匹配元素集合设置一个或多个属性/值对。</p><p><strong>注意：</strong>prop() 方法应该用于检索属性值，例如 DOM 属性（如 selectedIndex, tagName, nodeName, nodeType, ownerDocument, defaultChecked, 和 defaultSelected）。</p><p><strong>提示：</strong>如需检索 HTML 属性，请使用&nbsp;<a href=\"https://www.runoob.com/jquery/html-attr.html\" target=\"_blank\" style=\"color: rgb(143, 255, 143); --darkreader-inline-color:#8fff8f;\" data-darkreader-inline-color=\"\">attr()</a>&nbsp;方法代替。</p><p><strong>提示：</strong>如需移除属性，请使用&nbsp;<a href=\"https://www.runoob.com/jquery/html-removeprop.html\" target=\"_blank\" style=\"color: rgb(143, 255, 143); --darkreader-inline-color:#8fff8f;\" data-darkreader-inline-color=\"\">removeProp()</a>&nbsp;方法。</p>',0,0,0,0,'2020-08-04 23:45:26','2020-08-04 23:45:26',1);

#
# Structure for table "persistent_logins"
#

DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "persistent_logins"
#


#
# Structure for table "sys_role"
#

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `authority` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "sys_role"
#

INSERT INTO `sys_role` VALUES (1,'USER','浏览blog'),(2,'BLOG','写blog'),(3,'ADMIN','修改用户');

#
# Structure for table "sys_topic"
#

DROP TABLE IF EXISTS `sys_topic`;
CREATE TABLE `sys_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `branch` varchar(255) NOT NULL,
  `topic_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "sys_topic"
#

INSERT INTO `sys_topic` VALUES (1,'主版','简单交流'),(2,'主版','情感交流'),(3,'主版','技术交流'),(4,'主版','水水水');

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `username` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (0,'userpass','$2a$10$Sc6lFFKLbNtPxVX.2D8h9u8wb6qht5EgLsooVrdgL9BShA3SbNRau',NULL,NULL,NULL,'USER'),(1,'user1','$2a$10$u7bwoOUuIHp4DYlsHWMjjO8bwOy0a.HPg0/DRqOOfUtyyoMSOp3ge',NULL,NULL,NULL,'USER'),(2,'admin1','$2a$10$ou4wOJ.VY5dToAHY0pCzLOmN5iihQ73n2oeKxkJMc0OGFB0jG0Jia',NULL,NULL,NULL,'USER,ADMIN'),(4,'user2','$2a$10$QJMv7.Cu8hl0U5YjmvwfE.mOziZ9ZLHJICpeAy2KYc34PP5AjARsi',NULL,NULL,NULL,'BLOG'),(12,'user1a','$2a$10$AUJdH6pg5Ygm3BVaL8QXPeHMi7r900z57sVmXp0/jTu5WlL1N1r2.',NULL,NULL,NULL,'USER'),(13,'user2a','$2a$10$fmxe.ozANlQ7P63OvE3leu2UBz3LcYgHQ91uBEwX9gl5KscZlQjLK',NULL,NULL,NULL,'USER'),(14,'apple','$2a$10$q8EeZtxezMekv9XM9dncge1qHTApfvGueoVVuwBP0qbCowVcLKxUi',NULL,NULL,NULL,'USER'),(15,'apple1','$2a$10$uxtkfBcvTChCLPuTPapTgeDIn1uHzt82iESPvKceIvJ7Wp4QzREaW',NULL,NULL,NULL,'USER'),(16,'myuser','$2a$10$8jn3r8uVQtFM6AREfBsAverbU6TveP2xYrSRmdFE/d5.GUgLR.C9W',NULL,NULL,NULL,'USER,BLOG'),(1289131682022182912,'myuser01','$2a$10$6HzX9e6Y9jsRIFqSRINFKe6eCHd47x/s/7nWE2rJXy0jnI.8d3alO',NULL,NULL,NULL,'USER,BLOG'),(1289154850527051776,'myuser02','$2a$10$V/PK8VnOF6Toh1JjVD8qPO16WVzfYL9rVWKw.pfEQ4MokxkTUZvd6',NULL,NULL,NULL,'USER,BLOG'),(1289987687325032448,'myuser03','$2a$10$OUobNXt5T.Cx84xTkuxDQeiRACHl4UwDxP2FWavviMEwJsrP/JP/G',NULL,NULL,NULL,'USER,BLOG'),(1290274732928872448,'myuser04','$2a$10$1SYdjDP0oYohIOyb3FvSP.z4dUfaC.Adtr8Wx8lA8lT1V8jcOqXwG',NULL,NULL,NULL,'USER,BLOG');
