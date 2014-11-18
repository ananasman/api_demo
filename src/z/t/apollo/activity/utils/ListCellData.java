package z.t.apollo.activity.utils;

import android.content.Context;
import android.content.Intent;

/**
 * @
 * 
 * @author AnanasMan
 * 
 */
public class ListCellData {
	private String s = "";
	private Context c = null;
	private Intent i = null;

	public ListCellData(Context c, String s, Intent i) {
		this.s = s;
		this.c = c;
		this.i = i;
	}

	public void startActivity() {
		System.out.println("action" + getI().getAction());
		getC().startActivity(getI());
	}

	public String getS() {
		return s;
	}

	public Context getC() {
		return c;
	}

	public Intent getI() {
		return i;
	}

	@Override
	public String toString() {
		return getS();
	}
}
