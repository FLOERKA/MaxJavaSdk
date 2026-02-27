package ru.floerka.max.core.http.impl;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.HttpRequestObject;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.http.HttpConstants;
import ru.floerka.max.core.http.OkHttpService;
import ru.floerka.max.core.http.models.ClientResponse;
import ru.floerka.max.core.json.JsonConverter;
import ru.floerka.max.core.json.JsonUtils;
import ru.floerka.max.core.models.bot.BotInfo;
import ru.floerka.max.core.models.messages.Message;
import ru.floerka.max.core.models.messages.chats.Chat;
import ru.floerka.max.core.models.request.callback.CallbackAnswerRequest;
import ru.floerka.max.core.models.request.chat.*;
import ru.floerka.max.core.models.request.messages.EditMessageRequest;
import ru.floerka.max.core.models.request.messages.GetMessagesRequest;
import ru.floerka.max.core.models.request.messages.SendMessageRequest;
import ru.floerka.max.core.models.request.subscriptions.AddSubscriptionsRequest;
import ru.floerka.max.core.models.request.subscriptions.RemoveSubscriptionsRequest;
import ru.floerka.max.core.models.request.subscriptions.UpdatesRequest;
import ru.floerka.max.core.models.request.upload.UploadFileRequest;
import ru.floerka.max.core.models.response.chat.*;
import ru.floerka.max.core.models.response.messages.EditMessageResponse;
import ru.floerka.max.core.models.response.messages.GetMessagesResponse;
import ru.floerka.max.core.models.response.messages.SendMessageResponse;
import ru.floerka.max.core.models.response.subscriptions.AddSubscriptionsResponse;
import ru.floerka.max.core.models.response.subscriptions.GetSubscriptionsResponse;
import ru.floerka.max.core.models.response.subscriptions.RemoveSubscriptionsResponse;
import ru.floerka.max.core.models.response.subscriptions.UpdatesResponse;
import ru.floerka.max.core.models.response.video.VideoResponse;
import ru.floerka.max.core.models.upload.UploadFileResponse;
import ru.floerka.max.core.models.upload.file.InputFile;
import ru.floerka.max.core.models.user.ChatMember;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;


public class OkHttpMaxClient extends OkHttpService {


    public <A extends MaxObject, B extends MaxObject> B execute(String token, A object) {
        HttpRequestObject requestObject = new HttpRequestObject(object);

        String url = requestObject.getUrl();
        RequestBody body = requestObject.getRequestBody();
        HttpMethod method = requestObject.getMethod();

        Request.Builder request = buildRequest(url);
        switch (method) {
            case GET -> request.get();
            case PUT -> {
                if(body != null)
                    request.put(body);
            }
            case DELETE -> {
                if(body != null)
                    request.delete(body);
                else request.delete();
            }
            case POST -> {
                if(body != null)
                    request.post(body);
            }
            case PATCH -> {
                if(body != null)
                    request.patch(body);
            }
        }
        addAuthorization(request, token);


        ClientResponse response = getResponse(request.build());
        if(response != null && response.isSuccessful()) {
            String responseText = response.getText();
            if(responseText != null && JsonUtils.isJson(responseText)) {

                @SuppressWarnings("unchecked")
                B responseObject = (B) JsonConverter.fromObject(new JSONObject(responseText), requestObject.getResponseClazz());

                return responseObject;
            }
        }

        return null;
    }


    public BotInfo botInfo(String accessToken) {
        Request.Builder builder = buildRequest(HttpConstants.BASE_URL + HttpConstants.Appends.BOTS)
                .get();
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if (response != null && response.isSuccessful()) {
            String body = response.getText();
            if (body != null && JsonUtils.isJson(body)) {
                return JsonConverter.fromObject(new JSONObject(body), BotInfo.class);
            }
        }
        return null;
    }

    public ChatsGetResponse chats(String accessToken, int count, Long marker) {
        Request.Builder builder = buildRequest(HttpConstants.BASE_URL + HttpConstants.Appends.CHATS
        + "?count="+count+"&marker="+marker)
                .get();
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                if(object.has("chats") && object.has("marker")) {
                    JSONArray chatsArray = object.getJSONArray("chats");
                    Long marker2 = object.getLong("marker");
                    Chat[] chats = new Chat[chatsArray.length()];
                    for(int i = 0; i < chatsArray.length(); i++) {
                        JSONObject chatObject = chatsArray.getJSONObject(i);
                        chats[i] = JsonConverter.fromObject(chatObject, Chat.class);
                    }
                    return new ChatsGetResponse(chats, marker2);
                }
            }
        }
        return null;
    }

    public Chat chat(String accessToken, long chatID) {
        Request.Builder builder = buildRequest(HttpConstants.BASE_URL + HttpConstants.Appends.CHATS + "/" + chatID)
                .get();
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, Chat.class);
            }
        }
        return null;
    }

    public void patchChat(String accessToken, long chatID, ChatPatchRequest chatPatch) {
        RequestBody body = createBody(JsonConverter.getObject(chatPatch));
        Request.Builder builder = buildRequest(HttpConstants.BASE_URL + HttpConstants.Appends.CHATS + "/" + chatID)
                .patch(body);
        addAuthorization(builder, accessToken);
        cleanExecute(builder.build());
    }

    public ChatDeleteResponse deleteChat(String accessToken, long chatID) {
        Request.Builder builder = buildRequest(HttpConstants.BASE_URL + HttpConstants.Appends.CHATS + "/" + chatID)
                .delete();
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, ChatDeleteResponse.class);
            }
        }
        return null;
    }

    public ChatActionResponse actionChat(String accessToken, long chatID, ChatActionResponse action) {
        JSONObject post = JsonConverter.getObject(action);
        Request.Builder builder = buildRequest(HttpConstants.BASE_URL + HttpConstants.Appends.CHATS + "/" + chatID + "/actions")
                .post(createBody(post));
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, ChatActionResponse.class);
            }
        }
        return null;
    }

    public Message chatGetPinnedMessage(String accessToken, long chatID) {
        Request.Builder builder = buildRequest(HttpConstants.BASE_URL + HttpConstants.Appends.CHATS + "/" + chatID + "/pin")
                .get();
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, Message.class);
            }
        }
        return null;
    }

    public ChatPinResponse chatPinMessage(String accessToken, long chatID, ChatPinRequest request) {
        JSONObject put = JsonConverter.getObject(request);
        Request.Builder builder = buildRequest(HttpConstants.BASE_URL + HttpConstants.Appends.CHATS + "/" + chatID + "/pin")
                .put(createBody(put));
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, ChatPinResponse.class);
            }
        }
        return null;
    }

    public ChatPinResponse chatUnpinMessage(String accessToken, long chatID) {
        Request.Builder builder = buildRequest(HttpConstants.BASE_URL + HttpConstants.Appends.CHATS + "/" + chatID + "/pin")
                .delete();
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, ChatPinResponse.class);
            }
        }
        return null;
    }

    public ChatMember chatInInfo(String accessToken, long chatID) {
        Request.Builder builder = buildRequest(HttpConstants.BASE_URL + HttpConstants.Appends.CHATS + "/" + chatID + "/" +
                HttpConstants.Appends.Chats.MEMBERS + "/me")
                .get();
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, ChatMember.class);
            }
        }
        return null;
    }

    public DeleteFromChatResponse deleteFromChat(String accessToken, long chatID) {
        Request.Builder builder = buildRequest(HttpConstants.BASE_URL + HttpConstants.Appends.CHATS + "/" + chatID + "/" +
                HttpConstants.Appends.Chats.MEMBERS + "/me")
                .delete();
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, DeleteFromChatResponse.class);
            }
        }
        return null;
    }

    public ChatAdminsResponse chatAdmins(String accessToken, long chatID) {
        Request.Builder builder = buildRequest(HttpConstants.BASE_URL + HttpConstants.Appends.CHATS + "/" + chatID + "/" +
                HttpConstants.Appends.Chats.MEMBERS + "/admins")
                .delete();
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, ChatAdminsResponse.class);
            }
        }
        return null;
    }

    public ChatResponse addChatAdmin(String accessToken, long chatID, ChatAddAdminsRequest request) {
        JSONObject post = JsonConverter.getObject(request);
        Request.Builder builder = buildRequest(HttpConstants.BASE_URL + HttpConstants.Appends.CHATS + "/" + chatID + "/" +
                HttpConstants.Appends.Chats.MEMBERS + "/admins")
                .post(createBody(post));
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, ChatResponse.class);
            }
        }
        return null;
    }

    public ChatResponse deleteChatAdmin(String accessToken, long chatID, long userID) {
        Request.Builder builder = buildRequest(HttpConstants.BASE_URL + HttpConstants.Appends.CHATS + "/" + chatID + "/" +
                HttpConstants.Appends.Chats.MEMBERS + "/admins" + "/" + userID)
                .delete();
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, ChatResponse.class);
            }
        }
        return null;
    }

    public ChatMembersResponse chatMembers(String accessToken, ChatMembersRequest request) {
        String url = HttpConstants.BASE_URL + HttpConstants.Appends.CHATS + "/" + request.getChatId() + "/" +
                HttpConstants.Appends.Chats.MEMBERS + "/members";
        url = url + "?user_ids="+request.getUserIds();
        if(request.getMarker() != null) {
            url = url + "&marker="+request.getMarker();
        }
        if(request.getCount() != null) {
            url = url + "&count="+request.getCount();
        }

        Request.Builder builder = buildRequest(url)
                .get();
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, ChatMembersResponse.class);
            }
        }
        return null;
    }

    public ChatResponse chatAddMembers(String accessToken, long chatId, ChatAddMembersRequest request) {
        String url = HttpConstants.BASE_URL + HttpConstants.Appends.CHATS + "/" + chatId + "/" +
                HttpConstants.Appends.Chats.MEMBERS;

        JSONObject object1 = JsonConverter.getObject(request);

        Request.Builder builder = buildRequest(url)
                .post(createBody(object1));
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, ChatResponse.class);
            }
        }
        return null;
    }

    public ChatResponse chatDeleteMembers(String accessToken, long chatId, long userId, boolean block) {
        String url = HttpConstants.BASE_URL + HttpConstants.Appends.CHATS + "/" + chatId + "/" +
                HttpConstants.Appends.Chats.MEMBERS + "?user_id="+userId + "&block="+block;

        Request.Builder builder = buildRequest(url)
                .delete();
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, ChatResponse.class);
            }
        }
        return null;
    }
    public ChatResponse chatDeleteMembers(String accessToken, long chatId, long userId) {
        return chatDeleteMembers(accessToken, chatId, userId, false);
    }



    // SUBSCRIPTIONS

    public GetSubscriptionsResponse getSubscriptions(String accessToken) {
        String url = HttpConstants.BASE_URL + HttpConstants.Appends.SUBSCRIPTIONS;

        Request.Builder builder = buildRequest(url)
                .get();
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, GetSubscriptionsResponse.class);
            }
        }
        return null;
    }

    public AddSubscriptionsResponse registerSubscriptions(String accessToken, AddSubscriptionsRequest request) {
        String url = HttpConstants.BASE_URL + HttpConstants.Appends.SUBSCRIPTIONS;

        JSONObject parsed = JsonConverter.getObject(request);

        Request.Builder builder = buildRequest(url)
                .post(createBody(parsed));
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, AddSubscriptionsResponse.class);
            }
        }
        return null;
    }

    public RemoveSubscriptionsResponse unregisterSubscriptions(String accessToken, RemoveSubscriptionsRequest request) {
        String url = HttpConstants.BASE_URL + HttpConstants.Appends.SUBSCRIPTIONS + "?url="+request.getUrl();

        Request.Builder builder = buildRequest(url)
                .delete();
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, RemoveSubscriptionsResponse.class);
            }
        }
        return null;
    }

    public UpdatesResponse getUpdates(String accessToken, UpdatesRequest request) {
        String url = HttpConstants.BASE_URL + HttpConstants.Appends.SUBSCRIPTIONS;
        url = url + "?check=true";
        if (request.getLimit() != null && request.getLimit() > 0) {
            url = url + "&limit=" + request.getLimit();
        }
        if (request.getTimeout() != null && request.getTimeout() > 0) {
            url = url + "&timeout=" + request.getTimeout();
        }
        if (request.getMarker() != null) {
            url = url + "&marker=" + request.getMarker();
        }
        if(request.getTypes() != null) {
            String append = String.join(",", request.getTypes());
            url = url + "&types="+append;
        }

        JSONObject parsed = JsonConverter.getObject(request);

        Request.Builder builder = buildRequest(url)
                .delete();
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, UpdatesResponse.class);
            }
        }
        return null;
    }


    // UPLOAD


    public CompletableFuture<UploadFileResponse> uploadFile(String accessToken, UploadFileRequest request) {
        String url = HttpConstants.BASE_URL + HttpConstants.Appends.UPLOAD;
        url = url + "?type="+request.getType().name().toLowerCase();

        Request.Builder builder = buildRequest(url)
                .post(RequestBody.create(null, new byte[0]));
        addAuthorization(builder, accessToken);

        return CompletableFuture.supplyAsync(() -> {
            try (ClientResponse response = getResponse(builder.build())) {
                if (response != null && response.isSuccessful() && response.getText() != null) {
                    String body = response.getText();
                    JSONObject json = new JSONObject(body);

                    String uploadUrl = json.getString("url");
                    String preToken = json.optString("token", null);

                    return performUpload(uploadUrl, accessToken, request.getFile(), preToken);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    private UploadFileResponse performUpload(String uploadUrl, String token, InputFile file, String preToken) {
        RequestBody streamBody = file.toRequestBody(MediaType.parse("application/octet-stream"));

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("data", file.getFileName(), streamBody)
                .build();

        Request.Builder uploadRequestBuilder = new Request.Builder()
                .url(uploadUrl)
                .post(requestBody);
        addAuthorization(uploadRequestBuilder, token);

        try (ClientResponse response = getResponse(uploadRequestBuilder.build())) {
            if (response != null && response.isSuccessful() && response.getText() != null) {
                String resultBody = response.getText();
                JSONObject resultJson = new JSONObject(resultBody);

                return new UploadFileResponse(preToken != null ? preToken : resultJson.getString("token"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                file.getInputStream().close();
            } catch (IOException ignore) {}
        }
        return null;
    }


    // MESSAGES


    public GetMessagesResponse getMessages(String accessToken, GetMessagesRequest request) {
        HttpUrl.Builder url = HttpUrl.parse(HttpConstants.BASE_URL + HttpConstants.Appends.MESSAGES).newBuilder();

        if(request.getChatId() != null) {
            url.addQueryParameter("chat_id", request.getChatId());
        }
        if(request.getMessageIds() != null) {
            url.addQueryParameter("message_ids", request.getMessageIds());
        }
        if(request.getFrom() != null) {
            url.addQueryParameter("from", request.getFrom().toString());
        }
        if(request.getTo() != null) {
            url.addQueryParameter("to", request.getTo().toString());
        }
        if(request.getCount() != null) {
            url.addQueryParameter("count", request.getCount().toString());
        }

        Request.Builder builder = buildRequest(url.build().toString())
                .delete();
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, GetMessagesResponse.class);
            }
        }
        return null;
    }

    public SendMessageResponse sendMessage(String accessToken, SendMessageRequest request) {
        HttpUrl.Builder url = HttpUrl.parse(HttpConstants.BASE_URL + HttpConstants.Appends.MESSAGES).newBuilder();

        if(request.getChatId() != null) {
            url.addQueryParameter("chat_id", request.getChatId().toString());
        }
        if(request.getUserId() != null) {
            url.addQueryParameter("user_id", request.getUserId().toString());
        }
        if(request.getDisableLinkPreview() != null) {
            url.addQueryParameter("disable_link_preview", request.getDisableLinkPreview().toString());
        }

        Request.Builder builder = buildRequest(url.build().toString())
                .post(createBody(JsonConverter.getObject(request.getBody())));
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, SendMessageResponse.class);
            }
        }
        return null;
    }

    public EditMessageResponse editMessage(String accessToken, EditMessageRequest request) {
        HttpUrl.Builder url = HttpUrl.parse(HttpConstants.BASE_URL + HttpConstants.Appends.MESSAGES).newBuilder();

        if(request.getMessageId() != null) {
            url.addQueryParameter("message_id", request.getMessageId());
        }

        Request.Builder builder = buildRequest(url.build().toString())
                .put(createBody(JsonConverter.getObject(request.getBody())));
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, EditMessageResponse.class);
            }
        }
        return null;
    }

    public ChatResponse deleteMessage(String accessToken, int messageId) {
        HttpUrl.Builder url = HttpUrl.parse(HttpConstants.BASE_URL + HttpConstants.Appends.MESSAGES).newBuilder();

        url.addQueryParameter("message_id", String.valueOf(messageId));

        Request.Builder builder = buildRequest(url.build().toString())
                .delete();
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, ChatResponse.class);
            }
        }
        return null;
    }

    public Message getMessage(String accessToken, String mid) {
        HttpUrl.Builder url = HttpUrl.parse(HttpConstants.BASE_URL + HttpConstants.Appends.MESSAGES).newBuilder();

        url.addQueryParameter("messageId", mid);

        Request.Builder builder = buildRequest(url.build().toString())
                .get();
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, Message.class);
            }
        }
        return null;
    }

    public VideoResponse getVideoInfo(String accessToken, String videoToken) {
        HttpUrl.Builder url = HttpUrl.parse(HttpConstants.BASE_URL + HttpConstants.Appends.VIDEOS).newBuilder();

        url.addQueryParameter("videoToken", videoToken);

        Request.Builder builder = buildRequest(url.build().toString())
                .get();
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, VideoResponse.class);
            }
        }
        return null;
    }

    public ChatResponse callbackAnswer(String accessToken, CallbackAnswerRequest request) {
        HttpUrl.Builder url = HttpUrl.parse(HttpConstants.BASE_URL + HttpConstants.Appends.ANSWERS).newBuilder();

        url.addQueryParameter("callback_id", request.getCallbackId());

        Request.Builder builder = buildRequest(url.build().toString())
                .post(createBody(JsonConverter.getObject(request.getBody())));
        addAuthorization(builder, accessToken);

        ClientResponse response = getResponse(builder.build());
        if(response != null && response.isSuccessful()) {
            String body = response.getText();
            if(body != null && JsonUtils.isJson(body)) {

                JSONObject object = new JSONObject();
                return JsonConverter.fromObject(object, ChatResponse.class);
            }
        }
        return null;
    }


    public void addAuthorization(Request.Builder builder, String token) {
        builder.addHeader("Authorization", token);
    }
}
