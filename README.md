# JsonCSVBridge
[![](https://jitpack.io/v/hyunolike/json-csv-bridge.svg)](https://jitpack.io/#hyunolike/json-csv-bridge)
> Inner Circle BE 1기 오픈소스 라이브러리 프로젝트

JSON에 맞춰 자동으로 CSV파일을 생성해주는 라이브러리 입니다.

### 🧑🏼‍🎨오픈소스 사용 모범사례 흐름도
<img width="1044" alt="image" src="https://github.com/user-attachments/assets/f49bbe60-8b2d-4a31-bacb-5b48bb87ec99">

### 🧑🏼‍🌾개발 일지
- [[설계/최종본] 클래스다이어그램](https://github.com/hyunolike/json-csv-bridge/wiki/%EA%B0%9C%EB%B0%9C%EA%B8%B0%EB%A1%9D-05.-%ED%81%B4%EB%9E%98%EC%8A%A4-%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8-%EC%B5%9C%EC%A2%85%EB%B3%B8)
- [[설계] 클래스다이어그램](https://github.com/hyunolike/json-csv-bridge/wiki/%EA%B0%9C%EB%B0%9C%EA%B8%B0%EB%A1%9D-03.-%08%ED%81%B4%EB%9E%98%EC%8A%A4-%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8-%EC%84%A4%EA%B3%84)

---
## Features
- 기본. JSON 값 CSV 파일 변환 생성
- 커스텀 CSV 필드 매핑
  - 사용자가 JSON 필드를 CSV 열과 수동으로 매핑할 수 있도록 지원합니다. 예를 들어, JSON의 특정 필드를 CSV의 특정 열로 지정할 수 있습니다.
- 데이터 검증 및 정제
  -  JSON 데이터를 CSV로 변환하기 전에 데이터 타입, 필수 필드 확인, 누락된 값 처리 등을 검증하고 정제하는 기능을 추가합니다.
- CSV 포맷 설정
  - CSV 파일의 구분자, 인코딩 방식, 행 구분자 등의 포맷을 사용자 정의할 수 있도록 지원합니다.
- 데이터 필터링 및 선택적 변환
  - JSON의 특정 필드만 변환하거나, 특정 조건을 만족하는 데이터만 필터링하여 변환하는 기능을 제공합니다.
- 병합 및 합치기
  - 여러 JSON 파일을 하나의 CSV 파일로 병합하거나, JSON 데이터를 기존 CSV 파일에 추가하는 기능을 제공합니다.
- 변환 후 후처리 작업
  - 변환 후 CSV 파일에 대해 추가적인 후처리 작업(예: 특정 열 삭제, 추가, 순서 변경)을 할 수 있는 기능을 제공합니다.

## Dependencies
Depends on:
- Java 21
- Kotlin 1.9
- Spring Boot 3.3

## Include in your project 
```kotlin
//의존성 추가
```

## Usage
### 기본. JSON 값 CSV 파일 변환 생성
```kotlin
// 기본. JSON 값 CSV 파일 변환 생성
```
