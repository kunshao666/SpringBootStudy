package com.kunshao.springbootstudy.common;

public class Constant {

	public static final String PSE_LINK_PENDING_PREFIX = "PSE:LINK:PENDING:"; // set site link

	public static final String PSE_LINK_PROCESSING = "PSE:LINK:PROCESSING"; // set pending link

	public static final String PSE_LINK_PROCESSED_PREFIX = "PSE:LINK:PROCESSED:"; // set site link

	public static final String PSE_SITE_POLICY = "PSE:SITE:POLICY";

	public static final String PSE_SITE_EFFECTIVE_POLICY = "PSE:SITE:POLICY:EFFECTIVE";

	public static final String PSE_SITE_POLICY_PREFIX = "PSE:SITE:POLICY:";

	public static final String PSE_SITE_EFFECTIVE_POLICY_PREFIX = "PSE:SITE:POLICY:EFFECTIVE:";

	@Deprecated
	public static final String PSE_SITE_LINK_PREFIX = "PSE:SITE:LINK:";

	public static final String PSE_SPIDER_MEMBER = "PSE:SPIDER:MEMBER";

	public static final String PSE_SPIDER_CONSOLE = "PSE:SPIDER:CONSOLE";

	public static final String PSE_SPIDER_LOG_PREFIX = "PSE:SPIDER:LOG:";

	public static final String FASTDFS_GROUP_ATTACHMENT = "group1";

	public static final String FASTDFS_GROUP_PAGEFILE = "group2";

	public static final String PSE_SEED_LOAD_TIME_PREFIX = "PSE:SEED:LOAD:TIME:"; // 天	hash:key	time	count/time

	public static final String PSE_SEED_LOAD_COUNT_PREFIX = "PSE:SEED:LOAD:COUNT:";

	public static final String PSE_SEED_SPEED_TIME_PREFIX = "PSE:SEED:SPEED:TIME:"; // 小时

	public static final String PSE_SEED_SPEED_COUNT_PREFIX = "PSE:SEED:SPEED:COUNT:";

	public static final String PSE_SEED_FAIL_COUNT_PREFIX = "PSE:SEED:FAIL:COUNT:"; // 小时失败

	// 离开driver的条件,tab页数
	public static final Integer QUIT_TAB_SIZE = 1;

	// 关闭driver的倍数
	public static final Integer CLOSE_DRIVER_DOUBLE = 2;
	public static final String SPIDER_PRODUCER_COUNT = "SPIDER_PRODUCER_COUNT";
	public static final String SPIDER_CLOSE_DRIVER_DOUBLE = "SPIDER_CLOSE_DRIVER_DOUBLE";

	//API接口获取数据条数
	public static final Integer DEFAULT_API_DATA_COUNT = 10;
}
