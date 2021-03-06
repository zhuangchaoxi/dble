/*
 * Copyright (C) 2016-2018 ActionTech.
 * License: http://www.gnu.org/licenses/gpl.html GPL version 2 or higher.
 */

package com.actiontech.dble.manager.handler;

import com.actiontech.dble.config.ErrorCode;
import com.actiontech.dble.manager.ManagerConnection;
import com.actiontech.dble.manager.response.OnOffSlowQueryLog;
import com.actiontech.dble.route.parser.ManagerParseOnOff;

public final class DisableHandler {
    private DisableHandler() {
    }

    public static void handle(String stmt, ManagerConnection c, int offset) {
        int rs = ManagerParseOnOff.parse(stmt, offset);
        switch (rs & 0xff) {
            case ManagerParseOnOff.SLOW_QUERY_LOG:
                OnOffSlowQueryLog.execute(c, false);
                break;
            default:
                c.writeErrMessage(ErrorCode.ER_YES, "Unsupported statement");
        }
    }
}

