package com.github.dockerjava.jaxrs;

import static javax.ws.rs.client.Entity.entity;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.api.command.ExecCreateCmd;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.command.ExecStartCmdResponse;
import com.github.dockerjava.api.command.ExecStartCmdSync;
import com.github.dockerjava.core.DockerClientConfig;

public class ExecStartCmdSyncExec extends AbstrSyncDockerCmdExec<ExecStartCmdSync, ExecStartCmdResponse> implements
        ExecStartCmdSync.Exec {

    private static final Logger LOGGER = LoggerFactory.getLogger(VersionCmdExec.class);

    public ExecStartCmdSyncExec(WebTarget baseResource, DockerClientConfig dockerClientConfig) {
        super(baseResource, dockerClientConfig);
    }

    @Override
    protected ExecStartCmdResponse execute(ExecStartCmdSync command) {
    	 WebTarget webTarget = getBaseResource().path("/exec/{id}/start").resolveTemplate("id", command.getExecId());

         System.out.println("POST: {} "+webTarget);
         LOGGER.trace("POST: {}", webTarget);

        return webTarget.request().accept(MediaType.APPLICATION_JSON)
                .post(entity(command, MediaType.APPLICATION_JSON), ExecStartCmdResponse.class);
    }
}
