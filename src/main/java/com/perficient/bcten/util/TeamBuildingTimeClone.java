package com.perficient.bcten.util;

import java.util.Date;

public final class TeamBuildingTimeClone {
	private TeamBuildingTimeClone() {

	}

	public static Date getTeamBuildingTime(Date teamBuildingTime) {
		if (teamBuildingTime == null) {
			return null;
		} else {
			return (Date) teamBuildingTime.clone();
		}
	}
}
