package com.wlopezob.personav1.config.logging;

import org.reactivestreams.Subscription;
import org.slf4j.MDC;
import reactor.core.CoreSubscriber;
import reactor.util.context.Context;

public class MdcContextLifter<T> implements CoreSubscriber<T> {

  private final CoreSubscriber<T> coreSubscriber;

  public MdcContextLifter(CoreSubscriber<T> coreSubscriber) {
    this.coreSubscriber = coreSubscriber;
  }

  @Override
  public void onSubscribe(Subscription s) {
    coreSubscriber.onSubscribe(s);
  }

  @Override
  public void onNext(T t) {
    copyToMdc(currentContext());
    coreSubscriber.onNext(t);
  }

  @Override
  public void onError(Throwable t) {
    copyToMdc(currentContext());
    coreSubscriber.onError(t);
  }

  @Override
  public void onComplete() {
    copyToMdc(currentContext());
    coreSubscriber.onComplete();
  }

  @Override
  public Context currentContext() {
    return coreSubscriber.currentContext();
  }

  private void copyToMdc(Context context) {
    context.stream()
        .filter(e -> e.getValue() instanceof String)
        .forEach(e -> MDC.put(e.getKey().toString(), (String) e.getValue()));
  }
}
