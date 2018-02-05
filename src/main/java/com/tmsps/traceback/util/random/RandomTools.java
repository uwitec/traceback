package com.tmsps.traceback.util.random;
//package com.tmsps.qihang.util.random;
//
//import java.util.List;
//
//import org.apache.commons.lang3.RandomUtils;
//
//import com.tmsps.qihang.model.t_shop_prize;
//
///**
// * 获取中奖概率
// * 
// * @author 冯晓东
// *
// */
//public class RandomTools {
//
//	public static boolean getRandom(int val) {
//		int r = RandomUtils.nextInt(0, 100);
//		if (r < val) {
//			return true;
//		}
//		return false;
//	}
//
//	/**
//	 * 抽奖过程，产品中奖纪录
//	 * 
//	 * @param listp
//	 * @return
//	 */
//	public static t_shop_prize getRandomPrize(List<t_shop_prize> listp) {
//		int totalChange = 0;
//		for (t_shop_prize map : listp) {
//			totalChange += map.getChance();
//		}
//		int r = RandomUtils.nextInt(0, totalChange);
//		int chance = 0;
//		for (t_shop_prize map : listp) {
//			chance += map.getChance();
//			if (r <= chance) {
//				return map;
//			}
//		}
//
//		return null;
//	}
//
//	public static void main(String[] args) {
//		int a = 0;
//		int b = 0;
//		for (int i = 0; i < 10000; i++) {
//			if (getRandom(10)) {
//				a++;
//			} else {
//				b++;
//			}
//		}
//		System.err.println(a);
//		System.err.println(b);
//	}
//}
