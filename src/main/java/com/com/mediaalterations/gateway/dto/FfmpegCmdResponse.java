package com.com.mediaalterations.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class FfmpegCmdResponse {
    private long pid;
    private String processId;
    private int progress;
    private String duration;
    private String finalFileSize;
    private ProcessStatus status;
}
