package com.github.dockerjava.api.command;

import java.io.InputStream;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.model.Frame;

public interface ExecStartCmdSync extends SyncDockerCmd<ExecStartCmdResponse> {

    @CheckForNull
    String getExecId();

    @CheckForNull
    Boolean hasDetachEnabled();

    @CheckForNull
    Boolean hasTtyEnabled();

    @CheckForNull
    InputStream getStdin();

    ExecStartCmdSync withDetach(Boolean detach);

    ExecStartCmdSync withExecId(@Nonnull String execId);

    ExecStartCmdSync withTty(Boolean tty);

    ExecStartCmdSync withStdIn(InputStream stdin);

    interface Exec extends DockerCmdSyncExec<ExecStartCmdSync, ExecStartCmdResponse> {
    }

}
