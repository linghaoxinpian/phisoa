package com.shmilyou.service.job;
/* Created with 岂止是一丝涟漪     530060499@qq.com    2018/11/8 */

import com.shmilyou.entity.OrganizationComment;
import com.shmilyou.entity.OrganizationLevel;
import com.shmilyou.entity.OrganizationOverview;
import com.shmilyou.service.OrganizationCommentService;
import com.shmilyou.service.OrganizationLevelService;
import com.shmilyou.service.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** 更新【organization_overview】表数据 */
@Component
public class OrganizationOverViewJob {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrganizationCommentService organizationCommentService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private OrganizationLevelService organizationLevelService;

    @Scheduled(cron = "0 0 0 1/1 * ?")
    public void updateAllScore() {
        List<OrganizationComment> list = organizationCommentService.loadScores();
        if (list.size() > 0) {
            //获取认证等级
            List<String> ids = new ArrayList<>();
            for (OrganizationComment c : list) {
                if (c.getOrganization() != null) {
                    String levelId = c.getOrganization().getLevelId();
                    ids.add(levelId);
                    continue;
                }
                logger.error("评论id：" + c.getId() + ",无对应的机构");
            }

            List<OrganizationLevel> levels = organizationLevelService.queryByIds(ids);
            List<String> levelIds = levels.stream().map(OrganizationLevel::getId).collect(Collectors.toList());

            List<OrganizationOverview> list1 = new ArrayList<>();
            list.forEach(i -> {
                OrganizationOverview overview = new OrganizationOverview();

                overview.setOrganizationId(i.getOrganizationId());
                overview.setEffectUltimate(i.getEffectScore());
                overview.setCreditUltimate(i.getCreditScore());
                overview.setEnvironmentUltimate(i.getEnvironmentScore());
                overview.setFacultyUltimate(i.getFacultyScore());
                overview.setSatisfactionUltimate(i.getSatisfactionScore());
                //匹配认证等级
                if (i.getOrganization() != null) {
                    OrganizationLevel level = levels.stream()
                            .filter(l -> l.getId().equals(i.getOrganization().getLevelId()))
                            .findAny().orElse(null);

                    overview.setCertificationLevel(level != null ? 50 : 0);
                    list1.add(overview);
                }
            });

            //更新
            organizationService.sync(list1);
        }
    }
}
