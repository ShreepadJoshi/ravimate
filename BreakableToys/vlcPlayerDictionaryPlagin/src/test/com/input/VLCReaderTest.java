package test.com.input;

import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Test;

import com.input.VLCReader;
import com.input.bean.VLCPlayerStatusBean;

public class VLCReaderTest {

	@Test
	public void testGetStatusOfVLCPlayer() {
		VLCReader vlcReader = new VLCReader();
		VLCPlayerStatusBean vlcPlayerStatusBean = vlcReader.getStatusOfVLCPlayer();
		assertNotNull(vlcPlayerStatusBean);		
	}

	@Test
	public void testGetStatusOfVLCPlayerURL() {
		fail("Not yet implemented");
	}
	
	
	

}
