package ivanchik.task.manager.api.pojo;

import java.sql.Timestamp;

public class Task {

    private Long id;
    private Long parentId;
    private Long userId;
    private String name;
    private String description;
    private Timestamp startDate;
    private Timestamp endDate;
    private int timeSpent = 0;
    private int progress = 0;
    private boolean daily;
    private boolean closed = false;

    public Long getId() {
        return id;
    }

    public Task setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public Task setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Task setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Task setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Task setStartDate(Timestamp startDate) {
        this.startDate = startDate;
        return this;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public Task setEndDate(Timestamp endDate) {
        this.endDate = endDate;
        return this;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public Task setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
        return this;
    }

    public int getProgress() {
        return progress;
    }

    public Task setProgress(int progress) {
        this.progress = progress;
        return this;
    }

    public boolean isDaily() {
        return daily;
    }

    public Task setDaily(boolean daily) {
        this.daily = daily;
        return this;
    }

    public boolean isClosed() {
        return closed;
    }

    public Task setClosed(boolean closed) {
        this.closed = closed;
        return this;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", timeSpent=" + timeSpent +
                ", progress=" + progress +
                ", daily=" + daily +
                ", closed=" + closed +
                '}';
    }
}
