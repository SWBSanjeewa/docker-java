package com.github.dockerjava.netty.exec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.ExecStartCmd;
import com.github.dockerjava.api.command.ExecStartCmdResponse;
import com.github.dockerjava.api.command.ExecStartCmdSync;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.netty.MediaType;
import com.github.dockerjava.netty.WebTarget;

public class ExecStartCmdSyncExec extends AbstrSyncDockerCmdExec<ExecStartCmdSync, ExecStartCmdResponse>
implements ExecStartCmdSync.Exec {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecStartCmdSyncExec.class);

    public ExecStartCmdSyncExec(WebTarget baseResource, DockerClientConfig dockerClientConfig) {
        super(baseResource, dockerClientConfig);
    }

    @Override
    protected ExecStartCmdResponse execute(ExecStartCmdSync command) {
        WebTarget webTarget = getBaseResource().path("/exec/{id}/start").resolveTemplate("id", command.getExecId());

       // return webTarget.request().accept(MediaType.APPLICATION_JSON).post(command, command);

        return webTarget.request().accept(MediaType.APPLICATION_JSON)
                .post(command, new TypeReference<ExecStartCmdResponse>() {
                });
  
    }
}
