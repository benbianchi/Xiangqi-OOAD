package xiangqi.AlphaXiangqi;

import static org.junit.Assert.*;

import org.junit.Test;

import xiangqi.XiangqiGameFactory;
import xiangqi.common.XiangqiGameVersion;

public class AlphaXiangqiTestCases {

	@Test
	public void factoryProducesAlphaXiangqi() {
		assertNotNull(XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.ALPHA_XQ));
	}

}
