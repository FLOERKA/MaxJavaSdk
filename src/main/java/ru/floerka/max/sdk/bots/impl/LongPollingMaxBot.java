package ru.floerka.max.sdk.bots.impl;

import ru.floerka.max.core.models.messages.update.Update;
import ru.floerka.max.core.models.request.subscriptions.UpdatesRequest;
import ru.floerka.max.core.models.response.subscriptions.UpdatesResponse;
import ru.floerka.max.sdk.bots.DefaultMaxBot;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class LongPollingMaxBot extends DefaultMaxBot {

    private long lastMarker = -1;
    private long millisPeriod = 10;
    private final ScheduledExecutorService executor;

    public LongPollingMaxBot(String token) {
        super(token);
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }
    public LongPollingMaxBot(String token, int millisTimeout) {
        super(token);
        this.executor = Executors.newSingleThreadScheduledExecutor();
        this.millisPeriod = millisTimeout;
    }

    @Override
    protected void setup() {
        executor.schedule(this::schedule, millisPeriod, TimeUnit.MILLISECONDS);
    }

    public abstract void update(Update update);
    public abstract void update(List<Update> updates);


    private void schedule() {
        List<Update> updates = checkUpdates();
        this.update(updates);
        updates.forEach(this::update);
    }

    private synchronized List<Update> checkUpdates() {
        UpdatesRequest request;
        UpdatesResponse response;
        if(lastMarker == -1) {
            request = new UpdatesRequest.Builder().build();
        } else {
            request = new UpdatesRequest.Builder().marker(lastMarker).build();
        }
        response = execute(request);

        List<Update> updates = response.getUpdatesAsList();
        if(response.getMarker() != null)
            lastMarker = response.getMarker();
        return updates;
    }

}
