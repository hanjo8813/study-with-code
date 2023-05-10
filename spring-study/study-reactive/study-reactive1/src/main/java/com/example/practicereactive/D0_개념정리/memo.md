## 명령형 vs 선언형

---

- 명령형
    - 무엇(What) 할건지를 나타내기 보다 어떻게(How)을 할 건지 설명하는 방식
    - 절차지향 프로그래밍 (Procedural Programming)
    - 객체지향 프로그래밍 (Object Oriendted Programming)
- 선언형
    - 어떻게(How) 할건지를 나타내기 보다 무엇(What)을 할 건지 설명하는 방식
    - 함수형 프로그래밍 (Functional Programming)
    - 반응형 프로그래밍 (Reactive Programming)
        - 비동기 이벤트 방식
        - Observer 패턴
        - Iterator 패턴

<br>

## Reactive

---

### Reactive Stream

- non-blocking backPressure을 이용하여 비동기 서비스를 할 때 기본이 되는 **스펙(인터페이스)**
- 특징
    - 스트리밍 방식
    - 비동기
    - 옵저버 + 반복자 패턴
    - 백프레셔
        - pub쪽에서 일방적 push가 아닌 sub쪽에서 원하는 만큼만 받을 수 있도록.. sub 요청에 의해 데이터 전달
        - subscription 활용
- 링크
    - https://github.com/reactive-streams/reactive-streams-jvm

### Flow API

- jdk 9에서 추가된 reactive streams 기반 api
- reactive streams랑 똑같이 생겼음

### RX

- Reactive eXtension (ReactiveX / RX)
- ReactiveX 라는 단체(마소)에서 만든 반응형 프로그래밍 라이브러리들
    - RxJava, RxSwift …
- 특징
    - reactive 세대 분류에서 2세대라 함
    - Reactive Stream 구현체 중 하나
- 링크
    - [https://reactivex.io/](https://reactivex.io/)

### Reactor (Flux)

- spring 관련 팀에서 만든 reactive 관련 오픈소스
    - spring 5에서 사용됨 (봄 친화적)
- 특징
    - 초기 버전은 백프레셔 없었음
    - reactive 세대 분류에서 4세대라 함
    - Reactive Stream 구현체 중 하나
- 링크
    - [https://projectreactor.io/docs/core/release/reference/index.html](https://projectreactor.io/docs/core/release/reference/index.html)
    - https://github.com/reactor/reactor-core

### Webflux

- Reactor 라이브러리를 사용해 구현된 프레임워크

### 순서 정리

1. observable
2. reactive streams
3. flow api
4. 구현체 라이브러리
    1. RX
    2. reactor
5. 프레임워크
    1. webflux