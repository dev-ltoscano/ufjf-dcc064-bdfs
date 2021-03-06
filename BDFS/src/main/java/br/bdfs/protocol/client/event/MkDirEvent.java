package br.bdfs.protocol.client.event;

import br.bdfs.event.DfsSendEvent;
import br.bdfs.event.message.DfsEventMessage;
import br.bdfs.exceptions.DfsException;
import br.bdfs.helper.LogHelper;
import br.bdfs.protocol.DfsAddress;
import br.bdfs.protocol.DfsProtocol;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author ltosc
 */
public class MkDirEvent extends DfsSendEvent
{
    public static final String EVENT_NAME = "MKDIR";
    
    private final String token;
    private final String path;
    private final String recursively;
    
    public MkDirEvent(DfsProtocol protocol, String token, String path, boolean recursively) 
    {
        super(protocol);
        
        this.token = token;
        this.path = path;
        this.recursively = String.valueOf(recursively);
    }

    @Override
    public DfsEventMessage sendEvent(DfsAddress remoteAddress, boolean waitForResponse) throws DfsException, IOException 
    {
        LogHelper.logDebug("MkDirEvent.sendEvent()");
        
        HashMap<String, String> paramList = new HashMap<>();
        paramList.put("TOKEN", token);
        paramList.put("PATH", path);
        paramList.put("RECURSIVELY", recursively);
        
        return sendMessage(remoteAddress, new DfsEventMessage(EVENT_NAME, paramList), waitForResponse);
    }
}
