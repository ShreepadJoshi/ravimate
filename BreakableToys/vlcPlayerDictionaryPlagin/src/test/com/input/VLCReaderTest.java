package test.com.input;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.input.VLCPlayerStatusReader;
import com.input.bean.VLCPlayerStatusBean;

public class VLCReaderTest {

	@Test
	public void testGetStatusOfVLCPlayer() {
		VLCPlayerStatusReader vlcReader = new VLCPlayerStatusReader();
		VLCPlayerStatusBean vlcPlayerStatusBean = vlcReader.getStatusOfVLCPlayer();
		assertNotNull(vlcPlayerStatusBean);		
	}

	@Test
	public void testGetStatusOfVLCPlayerURL() {
		fail("Not yet implemented");
	}
	
	
	

}
