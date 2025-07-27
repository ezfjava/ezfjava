/*
易支付JAVA版支付接口
技术交流: https://t.me/epayok
*/
import org.springframework.util.DigestUtils;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

 public class EPayAPIJava {

	public static void main(String[] args) {

		String url = "";

		String pid = "";

		String key = "";

		String signType = "MD5";



		String type = "";

		String outTradeNo = "";

		String notifyUrl = "";

		String returnUrl = "";

		String name = "";

		String money = "";


		

		Map<String,String> sign = new HashMap<>();
		sign.put("pid",pid);
		sign.put("type",type);
		sign.put("out_trade_no",outTradeNo);
		sign.put("notify_url",notifyUrl);
		sign.put("return_url",returnUrl);
		sign.put("name",name);
		sign.put("money",money);

		//根据key升序排序
		sign = sortByKey(sign);
		String signStr = "";
		//遍历map 转成字符串
		for(Map.Entry<String,String> m :sign.entrySet()){
			signStr += m.getKey() + "=" +m.getValue()+"&";
		}

	
		signStr = signStr.substring(0,signStr.length()-1);

	
		signStr += key;

		
		signStr = DigestUtils.md5DigestAsHex(signStr.getBytes());

		sign.put("sign_type",signType);

		sign.put("sign",signStr);

		System.out.println("<form id='paying' action='"+url+"/submit.php' method='post'>");

		for(Map.Entry<String,String> m :sign.entrySet()){

			System.out.println("<input type='hidden' name='"+m.getKey()+"' value='"+m.getValue()+"'/>");

		}

		System.out.println("<input type='submit' value='正在跳转'>");

		System.out.println("</form>");

		System.out.println("<script>document.forms['paying'].submit();</script>");

	}

	public static <K extends Comparable<? super K>, V > Map<K, V> sortByKey(Map<K, V> map) {
		Map<K, V> result = new LinkedHashMap<>();
		map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByKey()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
		return result;
	}

}
