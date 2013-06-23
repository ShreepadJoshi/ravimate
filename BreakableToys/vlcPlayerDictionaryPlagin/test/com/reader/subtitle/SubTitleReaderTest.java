package com.reader.subtitle;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bean.SubTitleBean;
import com.reader.vlc.VlcTime;
import com.util.Constants;

public class SubTitleReaderTest {

	private static final String FROM_WRONG_PATH = "";
	private static final String WRONG_PATH = "";
	private static final Map<String, SubTitleBean> EXPECTED_EMPTY_MAP = new HashMap<String, SubTitleBean>();

	URL pathOfTestSubtitleUrl = getClass().getResource("test_subtile.srt");
	final String FROM_TEST_SUBTITLE_FILE = pathOfTestSubtitleUrl.getPath();
	VlcTime vlcTime = new VlcTime("79");// 00:01:19
	VlcTime WRONG_VLC_TIME = new VlcTime("-1");// 00:01:21
	

	@Test
	public void should_read_subtitles_from_file() {

		// SubTitleReader subTitleReader = Mockito.mock(SubTitleReader.class);
		// jMockito.when(subTitleReader.isSubTitleAvailable()).thenReturn(true);

		SubTitleReader subTitleReader = new SubTitleReader(
				FROM_TEST_SUBTITLE_FILE);
		Map<String, SubTitleBean> timeSubTitleMap = subTitleReader
				.loadSubTitles();

		assertTrue(timeSubTitleMap.size() > 2);
	}

	@Test
	public void should_return_empty_Map_on_reload_subtitles_if_given_new_path_is_empty() {

		SubTitleReader subTitleReader = new SubTitleReader(
				FROM_TEST_SUBTITLE_FILE);

		Map<String, SubTitleBean> timeSubTitleMap = subTitleReader
				.reLoadSubTitles(FROM_WRONG_PATH);

		assertEquals("not getting empty map", timeSubTitleMap,
				EXPECTED_EMPTY_MAP);
	}

	@Test
	public void should_reload_subtitles_from_given_new_path() {
		SubTitleReader subTitleReader = new SubTitleReader(WRONG_PATH);

		Map<String, SubTitleBean> timeSubTitleMap = subTitleReader
				.loadSubTitles();

		subTitleReader.reLoadSubTitles(FROM_TEST_SUBTITLE_FILE);
		assertTrue(timeSubTitleMap.size() > 2);

	}

	//@Test
	public void should_return_empty_subtitle_for_worng_time() {
		//TODO update code to added end time also
		
		SubTitleReader subTitleReader = new SubTitleReader(WRONG_PATH);

		SubTitleBean expectedSubTitle = new SubTitleBean();
		

		subTitleReader.loadSubTitles();
		SubTitleBean subTitle = subTitleReader.getSubTitle(vlcTime);

		assertEquals("not getting subtilte",
				expectedSubTitle.getSubTitleText(), subTitle.getSubTitleText());
	}

	@Test
	public void should_return_subtitle_of_given_time_if_available_in_map() {
		SubTitleReader subTitleReader = new SubTitleReader(
				FROM_TEST_SUBTITLE_FILE);

		SubTitleBean expectedSubTitle = new SubTitleBean();
		expectedSubTitle.setSubTitleText("Ladies and gentlemen,"
				+ Constants.NEW_LINE + "this is your captain speaking.");

		subTitleReader.loadSubTitles();
		SubTitleBean subTitle = subTitleReader.getSubTitle(vlcTime);

		assertEquals("not getting subtilte",
				expectedSubTitle.getSubTitleText(), subTitle.getSubTitleText());
	}

}
