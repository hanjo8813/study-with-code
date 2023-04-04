### 실습 환경

- 23.04.04 기준
- java 11
- spring boot 2.7.10 (최신)
- spring batch 4.3.8


### jar 실행

```bash
java -jar old-0.0.1-SNAPSHOT.jar 'stringKey=jar' 'longKey(long)=3L' 'dateKey(date)=2023/04/04' 'doubleKey(double)=10.27' --spring.profiles.active=mysql
```

mac에서는 파라미터 부분에 '' 씌워줘야 함