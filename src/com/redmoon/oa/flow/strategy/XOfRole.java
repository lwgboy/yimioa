package com.redmoon.oa.flow.strategy;

import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import cn.js.fan.util.ErrMsgException;

import com.redmoon.kit.util.FileUpload;
import com.redmoon.oa.flow.MyActionDb;
import com.redmoon.oa.flow.WorkflowActionDb;
import com.redmoon.oa.flow.WorkflowDb;
import com.redmoon.oa.person.UserDb;

public class XOfRole implements IStrategy {
	private int x;

	public XOfRole() {
	}

	/**
	 * 
	 */
	public Vector selectUser(Vector userVector) {
		return userVector;
	}
	
	/**
	 * 是否處理者可以選擇
	 * @return
	 */
	public boolean isSelectable() {
		return false;
	}

	public boolean onActionFinished(HttpServletRequest request, WorkflowDb wf,
			MyActionDb mad) {
		if (mad.getActionAgreedCount() >= x) {
			Vector v = mad.getActionUnchecked();
			Iterator it = v.iterator();
			while (it.hasNext()) {
				MyActionDb md = (MyActionDb) it.next();
				md.setActionStatus(MyActionDb.CHECK_STATUS_PASS);
				md.setCheckDate(new java.util.Date());
			    md.setChecked(true);
			    md.save();
			}
		}
		return true;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void validate(HttpServletRequest request, WorkflowDb wf, WorkflowActionDb myAction, long myActionId, FileUpload fu, String[] userNames, WorkflowActionDb nextAction) throws ErrMsgException {
		
	}	
	
	/**
	 * 默认全部人员是否选中
	 */
	public boolean isSelected() {
		return false;
	}	

	public boolean isGoDown() {
		return false;
	}	
}
