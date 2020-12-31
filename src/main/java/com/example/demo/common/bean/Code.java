package com.example.demo.common.bean;
/**
 * 状态返回值
 */
public enum Code {

    // 请求成功 0 请求失败 > 0
    SUCCESS(0,"请求成功"),
    FLOW_BACK_SUCCESS(0,"流程撤销成功"),
    ERROR(1,"请求失败"),
    FEIGN_ERROR(1,"服务调用失败, 稍后再试"),
    DELETE_ERROR(1,"删除失败"),
    FDFS_UPLOAD_FILE_SUCCESS(0,"文件上传成功"),
    FDFS_FILE_NOT_EQ_ERROR(1,"文件上传数量不匹配"),
    FDFS_UPLOAD_FILE_FAIL(1,"文件上传失败"),
    FDFS_UPLOAD_FILE_TYPE_NOT_SUPPORT(1, "该文件类型不支持"),
    PWD_UPDATE_SUCCESS(0, "密码修改成功"),
    PACT_TRANSFER_SUCCESS(0,"合同转让成功"),
    IMPORT_EXCEL_SUCCESS(0, "Excel数据导入成功"),
    SHARE_SUCCESS(0, "分享成功"),
    FLOW_GO_BACK_SUCCESS(0, "流程已回退"),

    // 授权错误
    SET_AUTH_ERROR(401, "授权失败"),
    NOT_REQUEST_PERMISSION(403, "无访问或操作权限"),
    SYSTEM_ROLE_NOT_ALLOW_UPDATE(403,"系统角色不允许操作"),

    // 参数验证/数据校验 错误
    PARAMETER_ERROR(30400,"参数验证错误"),
    PACT_NUM_MUST_GT_ZERO(30400, "合同申请式数必须大于0"),
    PACT_COUNT_MUST_GT_ZERO(30400, "合同每式份数必须大于1且小于5"),
    PACT_FAIL_NOT_ALLOW_TRANSFER(30400, "遗失或损坏合同不允许转让"),
    PACT_FLOW_OR_USE_NOT_ALLOW_TRANSFER(30400, "审批中或已使用合同不允许转让"),
    MISS_REQUIRED_PARAMETER(30400,"缺少参数"),
    USERNAME_OR_PWD_ERROR(30400,"用户名或密码错误"),
    ROLE_NOT_FOUND(30400, "该角色不存在"),
    USER_NOT_FOUND(30400, "未找到该用户"),
    CUSTOMER_NOT_EXIST_OR_INVALID(30400, "客户不存在或无效"),
    NOT_FOUND_DATA_ERROR(30400,"数据不存在"),
    NAME_EXIST_ERROR(30400,"该名称已存在"),
    USER_NAME_EXIST_ERROR(30400, "用户名已存在"),
    WEBSITE_HAS_USED(30400, "该网址已被使用"),
    DATA_NUMBER_NOT_MATCH_ERROR(30400, "数据数量不匹配"),
    FIRM_BUSINESS_ID_NOT_NULL_ERROR(30400, "合作业务不能为空"),
    CONFIG_EXIST_ERROR(30400, "信息已存在, 无法添加"),
    PRIVATE_CUSTOMER_NUM_UPPER_LIMIT(30400, "私有客户数量已上限"),
    CUSTOMER_EXIST_GIVE_UP_ERROR(30400, "领取的客户中包含近期放弃的客户"),
    PRIVATE_CUSTOMER_BUSINESS_NUM_UPPER_LIMIT(30400, "领取的客户业务数量已上限"),
    EXIST_CUSTOMER_HAVE_BEEN_GET_ERROR(30400, "已被领取的客户不可领取"),
    EXIST_CUSTOMER_HAVE_BEEN_UPDATE_ERROR(30400, "此客户名称和业务已被保护"),
    ADVERSE_PRIVATE_CUSTOMER_NUM_UPPER_LIMIT(30400, "对方私有客户数量已上限"),
    TEMP_CUSTOMER_NUM_UPPER_LIMIT(30400, "临时客户数量已上限"),
    ADVERSE_PRIVATE_CUSTOMER_BUSINESS_NUM_UPPER_LIMIT(30400, "对方私有客户业务数量已上限"),
    FIRM_BUSINESS_DATE_NOT_IN_RANGE_ERROR(30400, "当前日期不在业务签约日期范围"),
    PACT_NOT_FOUND_ERROR(30400, "订单未关联合同"),
    PACT_HAS_USE_ERROR(30400, "该合同已被使用"),
    DATA_ERROR(30400, "数据错误"),
    EXCEL_TEMPLATE_ERROR(30400, "无法识别的Excel文件,请下载模板文件,进行导入!"),
    FLOW_NOT_EXIST(30400, "该审批不存在或已审批"),
    FLOW_NOT_FOUND(30400, "该审批不存在"),
    REFUND_FLOW_NOT_EXIST(30400, "该订单已退款或正在退款"),
    FLOW_ERROR(30400, "流程错误"),
    GET_RELATE_FLOW_FAIL(30400, "获取关联流程信息失败"),
    ORDER_UNKNOWN(30400, "未知订单类型"),
    FLOW_SETTING_NOT_FOUNT(30400,"审批流程不存在,请联系管理员"),
    FLOW_CONDITION_NOT_FOUND(30400, "流程条件不存在或有误"),
    FLOW_TYPE_ERROR(30400, "审批类型设定错误"),
    FLOW_NOT_FOUND_BU_LEADER(30400, "流程错误, 未找到负责人"),
    FLOW_NOT_FOUND_OPEN_LEADER(30400, "流程错误, 未找到开户负责人"),
    FLOW_NOT_FOUND_CHECK_LEADER(30400, "流程错误, 未找到查账负责人"),
    FLOW_NOT_FOUND_OPERATE_LEADER(30400, "流程错误, 未找到运营负责人"),
    FLOW_NOT_FOUND_PAY_LEADER(30400, "流程错误, 未找到充值人员"),
    REFUND_FLOW_HAS_NOT_TOP_UP_ERROR(30400, "流程错误, 该流程不支持充值流程"),
    FLOW_PASS_TIME_NOT_ENOUGH(30400, "距上次催办不足30分钟"),
    USER_NOT_NODE_AUDITOR(30400, "您不是当前节点审批人"),
    ORDER_DETAIL_ERROR(30400, "订单信息有误"),
    DEPT_TASK_NOT_FOUND_ERROR(30400, "父级任务不存在"),
    NOT_IN_DEPT_TASK_TIME_ERROR(30400, "该任务已过期"),
    DEPT_HAS_EXIST_LEADER_ERROR(30400, "该部门已设置主管"),
    DEPT_USER_DATA_CONFLICT(30400, "部门用户数据冲突"),
    FLOW_SETTING_HAS_EXIST(30400, "该审批流程已存在"),
    FLOW_COMMENT_NOT_PERMISSION(30400, "无评论权限"),
    HOSPITALITY_STANDARDS_ERROR(30400,"招待标准错误"),
    CHANNEL_NAME_ERROR(30400,"渠道名称不能为空"),
    FIRM_BUSINESS_ID_NULL(30400,"合作业务不能为空"),
    REMIT_ACCOUNT_NULL(30400,"打款户名不能为空"),
    REMIT_COMPANY_NULL(30400,"打款公司名不能为空"),
    PACT_ID_NULL(30400,"合同id不能为空"),
    WEBSITE_TYPE_ID_NULL(30400,"网站类型不能为空"),
    RECEIPT_TYPE_NULL(30400,"收费方式不能为空"),
    BANK_ID_NULL(30400,"到账银行不能为空"),
    INTO_MONEY_NULL(30400,"到账金额不能为空"),
    UP_TYPE_NULL(30400,"充值类型不能为空"),
    BIND_USER_ID_NULL(30400,"绑定销售不能为空"),
    ACCOUNT_ID_NULL(30400,"客户账户不能为空"),
    SETTLEMENT_NULL(30400,"结款金额不能为空"),
    ORDER_HAS_RECEIPT_EXIST_ERROR(30400,"该订单已有相关收据或正在审批"),



    // 业务操作错误
    CUSTOMER_NOT_SUFFICIENT_FUNDS(30500, "客户余额不足"),
    INSERT_ERROR(30500,"添加失败"),
    ORDER_INSERT_ERROR(30500, "发起订单失败"),
    REIMBURSEMENT_INSERT_ERROR(30500, "发起报销审批失败"),
    COST_INSERT_ERROR(30500, "发起费用审批失败"),
    SEAL_INSERT_ERROR(30500, "发起印鉴审批失败"),
    RECEIPT_INSERT_ERROR(30500, "发起收据审批失败"),
    SITE_INSERT_ERROR(30500, "发起网站审批失败"),
    PACT_INSERT_ERROR(30500, "发起合同审批失败"),
    REFUND_INSERT_ERROR(30500, "发起退款审批失败"),
    FIRM_BUSINESS_NOT_AVAILABLE(30500, "当前厂商业务不可用"),
    FIRM_BUSINESS_PO_NOT_EXIST(30500, "当前厂商业务未采购, 无法提单"),
    FIRM_BUSINESS_BALANCE_NOT_ENOUGH(30500, "该业务余额不足, 请联系业务负责人采购"),
    ORDER_DEDUCT_CURRENCY_FAIL(30500, "订单扣除币值失败"),
    PACT_TRANSFER_FAIL(30500, "合同转让失败"),
    FIRM_BUSINESS_PAY_ERROR(30500, "采购失败"),
    FIRM_BUSINESS_PAY_NOT_ZERO(30500, "采购不能为0"),
    GET_CUSTOMER_ERROR(30500, "领取客户失败"),
    GIVE_UP_CUSTOMER_ERROR(30500, "放弃客户失败"),
    OPERATE_DATA_ERROR(30500,"操作数据失败"),
    FIXED_DATA_UPDATE_ERROR(30500, "数据修改失败"),
    ORDER_BY_ERROR(30500,"排序失败"),
    CUSTOMER_BALANCE_NOT_ENOUGH(30500, "客户到账金额或余额不足"),
    PRIVATE_CUSTOMER_TRANSFER_FAIL(30500, "客户转让失败"),
    IMPORT_EXCEL_DATA_ERROR(30500, "导入Excel数据失败"),
    FLOW_PROCESS_FAIL(30500, "审批失败"),
    REFUND_TYPE_NOT_NULL_ERROR(30500, "退款类型不能为空"),
    FLOW_NOT_RIGHT_TYPE_ERROR(30500, "该审批类型不支持此操作"),
    FLOW_NOT_FINISH_ERROR(30500, "审批未完成, 无法执行此操作"),
    ORDER_HAS_ACCOUNT_ID_ERROR(30500, "该订单已绑定ID"),
    BIND_ACCOUNT_ID_ERROR(30500,"绑定账户ID失败"),
    ACCOUNT_ID_NOT_FOUNT(30500, "账户ID不存在"),
    ACCOUNT_ID_HAS_MANY(30500, "该账户ID存在多个, 请保证ID唯一性"),
    ACCOUNT_ID_EXIST_ERROR(30500, "该账户ID已存在"),
    GENERATE_PACT_NUMBER_ERROR(30500,"生成合同失败"),
    PWD_NOT_SAME(30500, "新密码不能与旧密码不能相同"),
    PWD_UPDATE_FIAL(30500, "密码修改失败, 请重试"),
    PWD_VALID_FIAL(30500, "旧密码输入错误"),
    SCHEDULE_TASK_ERROR(30500, "调度任务错误"),
    CRM_NOT_FOUND_SETTING(30500, "CRM设置信息未找到"),
    GET_SUMMARY_INFO_FAIL(30500, "生成审批摘要失败"),
    ADD_SOGOU_COST_FAIL(30500, "添加搜狗消耗失败"),
    ACCOUNT_AND_OPERATOR_NOT_EQUAL(30500, "账户与运营绑定不匹配"),
    SHARE_FAIL(30500, "分享失败"),

    // 异常
    EXCEPTION_ERROR(40500, "请求异常"),
    MQ_LOST_CONNECTION_EXCEPTION(40500, "与通讯服务器断开连接"),
    DOWNLOAD_FILE_ERROR(40500, "文件下载失败"),
    UPLOAD_FILE_SUFFIX_ERROR(40500, "文件扩展名不能为空");

    private Integer code;

    private String msg;

    /**
     * 返回实体
     * @param code
     * @param msg
     */
    Code(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
