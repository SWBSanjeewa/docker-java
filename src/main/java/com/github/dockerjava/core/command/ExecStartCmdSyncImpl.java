package com.github.dockerjava.core.command;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Collections.singletonMap;

import java.io.InputStream;

import javax.ws.rs.client.ResponseProcessingException;

import org.glassfish.jersey.message.internal.MessageBodyProviderNotFoundException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.ExecStartCmd;
import com.github.dockerjava.api.command.ExecStartCmdResponse;
import com.github.dockerjava.api.command.ExecStartCmdSync;
import com.github.dockerjava.api.exception.ConflictException;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.model.ContainerNetwork;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.command.CreateContainerCmdImpl.NetworkingConfig;

@JsonInclude(Include.NON_NULL)
public class ExecStartCmdSyncImpl extends AbstrDockerCmd<ExecStartCmdSync, ExecStartCmdResponse>
		implements ExecStartCmdSync {

	@JsonIgnore
	private String execId;

	@JsonProperty("Detach")
	private Boolean detach;

	@JsonProperty("Tty")
	private Boolean tty;

	@JsonIgnore
	private InputStream stdin;

	public ExecStartCmdSyncImpl(ExecStartCmdSync.Exec exec, String execId) {
		super(exec);
		withExecId(execId);
	}

	@Override
	public String getExecId() {
		return execId;
	}

	@Override
	public ExecStartCmdSync withExecId(String execId) {
		checkNotNull(execId, "execId was not specified");
		this.execId = execId;
		return this;
	}

	@Override
	public Boolean hasDetachEnabled() {
		return detach;
	}

	@Override
	public Boolean hasTtyEnabled() {
		return tty;
	}

	@Override
	@JsonIgnore
	public InputStream getStdin() {
		return stdin;
	}

	@Override
	public ExecStartCmdSync withDetach(Boolean detach) {
		this.detach = detach;
		return this;
	}

	@Override
	public ExecStartCmdSync withTty(Boolean tty) {
		this.tty = tty;
		return this;
	}

	@Override
	public ExecStartCmdSync withStdIn(InputStream stdin) {
		this.stdin = stdin;
		return this;
	}

	/**
	 * @throws NotFoundException
	 *             No such container
	 * @throws ConflictException
	 *             Named container already exists
	 */
	@Override
	public ExecStartCmdResponse exec() throws NotFoundException, ConflictException {
		try {
			ExecStartCmdResponse res =  super.exec();
		} catch (MessageBodyProviderNotFoundException|ResponseProcessingException e) {

		}
		return new ExecStartCmdResponse();
	}
}
