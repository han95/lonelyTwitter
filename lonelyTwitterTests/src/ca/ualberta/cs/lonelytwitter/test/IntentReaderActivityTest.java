package ca.ualberta.cs.lonelytwitter.test;

import ca.ualberta.cs.lonelytwitter.IntentReaderActivity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.widget.TextView;
import ca.ualberta.cs.lonelytwitter.R;

public class IntentReaderActivityTest extends ActivityInstrumentationTestCase2<IntentReaderActivity>{
	public IntentReaderActivityTest() {
		super(IntentReaderActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testSendTest(){
		String text = "Hello World";
		int code = IntentReaderActivity.NORMAL;
		IntentReaderActivity activity = startwithText(text, code);
		assertEquals("Did the text correct?", text, activity.getText());
	}
	
	public void testDoubleText() {
		String text = "Hello";
		IntentReaderActivity activity = startwithText(text, IntentReaderActivity.DOUBLE);
		assertEquals("Double transform working?", text+text, activity.getText());
	}
	
	public void testDisplayText() {
		String text = "Hello world";
		IntentReaderActivity activity = startwithText (text, IntentReaderActivity.NORMAL);
		assertEquals("correct text?", text, activity.getText());
		
		TextView view = (TextView) activity.findViewById(R.id.intentText);
		assertEquals("displays correct text?", text, view.getText().toString());
		
	}
	
	private IntentReaderActivity startwithText(String text, int code) {
		Intent intent = new Intent();
		intent.putExtra(IntentReaderActivity.TEXT_KEY, text);
		intent.putExtra(IntentReaderActivity.TRANSFORM_KEY, code);
		setActivityIntent(intent);
		return (IntentReaderActivity)getActivity();
	}
	
	public void testReverseText() {
		String text = "hello";
		IntentReaderActivity activity = startwithText(text, IntentReaderActivity.REVERSE);
		assertEquals("Reverse transform working?", "olleh", activity.getText());
	}
	
	public void testDefaultMessage() {
		String text = null;
		IntentReaderActivity activity = startwithText(text, IntentReaderActivity.NORMAL);
		TextView t = (TextView) activity.findViewById(R.id.intentText);
		ViewAsserts.assertOnScreen(t.getRootView(), t);
		assertEquals("Default message working?", "no text is sent!", t.getText());
	}
	
	public void testVisable() {
		String text = null;
		IntentReaderActivity activity = startwithText(text, IntentReaderActivity.NORMAL);
		View v = activity.getWindow().getDecorView();
		assertTrue("view is displayed on screen", v!=null);
	}
	
}
