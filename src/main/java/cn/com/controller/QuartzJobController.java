package cn.com.controller;

import cn.com.dto.QuartzJobDTO;
import cn.com.service.QuartzJobService;
import cn.com.vo.QuartzJobVO;
import cn.com.vo.ResponseVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 定时任务控制器
 *
 * @author william@StarImmortal
 * @date 2022/09/26
 */
@CrossOrigin
@RestController
@RequestMapping("/quartz")
@Validated
@Api(tags = "定时任务")
public class QuartzJobController {

	@Autowired
	private QuartzJobService quartzJobService;

	@ApiOperation(value = "调度定时任务")
	@PostMapping("/schedule")
	public ResponseVO scheduleJob(@RequestBody @Validated QuartzJobDTO dto) {
		quartzJobService.scheduleJob(dto);
		return ResponseVO.success();
	}

	@ApiOperation(value = "重新调度定时任务")
	@PostMapping("/reschedule")
	public ResponseVO rescheduleJob(@RequestBody @Validated QuartzJobDTO dto) {
		quartzJobService.rescheduleJob(dto);
		return ResponseVO.success();
	}

	@ApiOperation(value = "暂停定时任务")
	@PostMapping("/pause")
	public ResponseVO pauseJob(@RequestBody @Validated QuartzJobDTO dto) {
		quartzJobService.pauseJob(dto.getJobName(), dto.getJobGroupName());
		return ResponseVO.success();
	}

	@ApiOperation(value = "恢复定时任务")
	@PostMapping("/resume")
	public ResponseVO resumeJob(@RequestBody @Validated QuartzJobDTO dto) {
		quartzJobService.resumeJob(dto.getJobName(), dto.getJobGroupName());
		return ResponseVO.success();
	}

	@ApiOperation(value = "删除定时任务")
	@PostMapping("/delete")
	public ResponseVO deleteJob(@RequestBody @Validated QuartzJobDTO dto) {
		quartzJobService.deleteJob(dto.getJobName(), dto.getJobGroupName());
		return ResponseVO.success();
	}

	@ApiOperation(value = "查询所有任务")
	@PostMapping("/list")
	public ResponseVO<PageInfo<QuartzJobVO>> listJobs(@RequestBody Map map) {
		PageInfo<QuartzJobVO> jobs = quartzJobService.listJobs(map);
		return ResponseVO.success(jobs);
	}

}
