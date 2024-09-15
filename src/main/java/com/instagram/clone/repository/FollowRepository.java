package com.instagram.clone.repository;

import com.instagram.clone.entity.Follow;
import com.instagram.clone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    // للتحقق مما إذا كان المستخدم يتابع مستخدمًا آخر
    boolean existsByFollowerAndFollowing(User follower, User following);

    // لحذف عملية متابعة معينة
    void deleteByFollowerAndFollowing(User follower, User following);

    // استرجاع قائمة المتابعين لشخص معين
    List<Follow> findByFollowing(User following);

    // استرجاع قائمة الذين يتابعهم المستخدم
    List<Follow> findByFollower(User follower);
}
