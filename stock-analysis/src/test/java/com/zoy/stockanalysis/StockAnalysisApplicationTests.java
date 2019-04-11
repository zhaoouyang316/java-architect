package com.zoy.stockanalysis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

public class StockAnalysisApplicationTests {

	@Test
	public void test() {

		String str=(BigDecimal.valueOf(20000).divide(BigDecimal.valueOf(11.45333),BigDecimal.ROUND_HALF_UP)).toString();
		str=str.substring(0,str.length()-2)+"00";
		System.out.println(Long.valueOf(str));
	}

	@Test
	public void tes2t(){
		System.out.println(Double.valueOf("0.00")==0);
	}

}
